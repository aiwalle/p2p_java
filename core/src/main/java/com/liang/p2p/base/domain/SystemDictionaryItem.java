package com.liang.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

/**  数据字典明细
 * Created by liang on 2018/4/23.
 */
@Setter@Getter
public class SystemDictionaryItem extends BaseDomain {

    private Long parentId;
    private String title;
    private int squence;

}
