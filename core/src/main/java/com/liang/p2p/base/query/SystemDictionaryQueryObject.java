package com.liang.p2p.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 数据字典查询对象
 * Created by liang on 2018/4/27.
 */
@Setter@Getter
public class SystemDictionaryQueryObject extends QueryObject {

    private String keyword;

    private String parentId;

    public String getKeyword() {
        return StringUtils.hasLength(keyword) ? keyword : null;
    }

}
