package com.liang.p2p.business.service.impl;

import com.liang.p2p.business.domain.BidRequest;
import com.liang.p2p.business.mapper.BidRequestMapper;
import com.liang.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liang on 2018/5/17.
 */
@Service
public class BidRequestServiceImpl implements IBidRequestService {

    @Autowired
    private BidRequestMapper bidRequestMapper;


    public void update(BidRequest bidRequest) {
        int ret = bidRequestMapper.updateByPrimaryKey(bidRequest);
        if (ret == 0) {
            throw new RuntimeException("乐观锁失败   bidRequest:"
                    + bidRequest.getId());
        }
    }
}
