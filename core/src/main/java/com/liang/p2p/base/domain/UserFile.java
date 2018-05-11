package com.liang.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 风控材料
 * Created by liang on 2018/5/6.
 */
@Setter@Getter
public class UserFile extends BaseAuditDomain {

    private String image; // 风控材料图片
    private SystemDictionaryItem fileType; // 风控材料分类
    private int score; // 风控材料分值





}
