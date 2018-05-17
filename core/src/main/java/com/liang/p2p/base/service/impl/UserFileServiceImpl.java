package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.Iplog;
import com.liang.p2p.base.domain.SystemDictionaryItem;
import com.liang.p2p.base.domain.UserFile;
import com.liang.p2p.base.domain.Userinfo;
import com.liang.p2p.base.mapper.UserFileMapper;
import com.liang.p2p.base.query.PageResult;
import com.liang.p2p.base.query.UserFileQueryObject;
import com.liang.p2p.base.service.IUserFileService;
import com.liang.p2p.base.service.IUserinfoService;
import com.liang.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liang on 2018/5/6.
 */
@Service
public class UserFileServiceImpl implements IUserFileService {

    @Autowired
    private UserFileMapper userFileMapper;

    @Autowired
    private IUserinfoService userinfoService;

    public void apply(String fileName) {
        UserFile userFile = new UserFile();
        userFile.setApplier(UserContext.getCurrent());
        userFile.setApplyTime(new Date());
        userFile.setImage(fileName);
        userFile.setState(UserFile.STATE_NORMAL);
        userFileMapper.insert(userFile);
    }

    public List<UserFile> listFilesByHasType(Long logininfoId, boolean hasType) {
        return userFileMapper.listFilesByHasType(logininfoId, hasType);
    }

    public void batchUpdateFileType(Long[] ids, Long[] fileTypes) {
        for (int i = 0; i < ids.length; i++) {
            UserFile userFile = userFileMapper.selectByPrimaryKey(ids[i]);
            SystemDictionaryItem item = new SystemDictionaryItem();
            item.setId(fileTypes[i]);
            userFile.setFileType(item);
            userFileMapper.updateByPrimaryKey(userFile);
        }
    }

    public PageResult query(UserFileQueryObject queryObject) {
        int count = userFileMapper.queryForCount(queryObject);
        if (count >0) {
            List<UserFile> list = userFileMapper.query(queryObject);
            return new PageResult(list,count,queryObject.getCurrentPage(),queryObject.getPageSize());
        }
        return PageResult.empty(queryObject.getPageSize());
    }


    public void audit(Long id, int score, String remark, int state) {
        // 找到userfile,确定userfile的状态为未审核
        UserFile userFile = userFileMapper.selectByPrimaryKey(id);
        if (userFile != null && userFile.getState() == UserFile.STATE_NORMAL) {
            // 设置通用属性
            userFile.setAuditor(UserContext.getCurrent());
            userFile.setApplyTime(new Date());
            userFile.setState(state);
            // 如果审核通过，添加认证分数
            if (state == UserFile.STATE_AUDIT) {
                userFile.setScore(score);
                Userinfo userinfo = userinfoService.get(userFile.getApplier().getId());
                userinfo.setScore(userinfo.getScore() + score);
                userinfoService.update(userinfo);
            }
            userFileMapper.updateByPrimaryKey(userFile);
        }
    }
}
