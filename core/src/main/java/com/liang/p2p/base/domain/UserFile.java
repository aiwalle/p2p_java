package com.liang.p2p.base.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 风控材料
 * Created by liang on 2018/5/6.
 */
@Setter@Getter
public class UserFile extends BaseAuditDomain {

    private String image; // 风控材料图片
    private SystemDictionaryItem fileType; // 风控材料分类
    private int score; // 风控材料分值


    public String getJsonString() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("applier", this.applier.getUsername());
        json.put("fileType", this.fileType.getTitle());
        json.put("image", image);
        return JSONObject.toJSONString(json);
    }


}
