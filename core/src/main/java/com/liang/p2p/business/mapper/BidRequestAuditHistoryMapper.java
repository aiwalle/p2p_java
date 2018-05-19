package com.liang.p2p.business.mapper;

import com.liang.p2p.business.domain.BidRequestAuditHistory;

public interface BidRequestAuditHistoryMapper {

    int insert(BidRequestAuditHistory record);

    BidRequestAuditHistory selectByPrimaryKey(Long id);

}