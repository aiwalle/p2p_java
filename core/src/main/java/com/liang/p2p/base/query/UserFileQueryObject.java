package com.liang.p2p.base.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户风控文件查询对象
 * Created by liang on 2018/5/17.
 */
@Getter@Setter
public class UserFileQueryObject extends AuditQueryObject {
    private Long applierId;
}
