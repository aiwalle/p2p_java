package com.liang.p2p.base.service.impl;

import com.liang.p2p.base.domain.SystemDictionary;
import com.liang.p2p.base.domain.SystemDictionaryItem;
import com.liang.p2p.base.domain.UserFile;
import com.liang.p2p.base.mapper.UserFileMapper;
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

    public void apply(String fileName) {
        UserFile userFile = new UserFile();
        userFile.setApplier(UserContext.getCurrent());
        userFile.setApplyTime(new Date());
        userFile.setImage(fileName);
        userFile.setState(UserFile.STATE_NORMAL);
        userFileMapper.insert(userFile);
    }

    public List<UserFile> listUnTypeFiles(Long logininfoId) {
        return userFileMapper.listUnTypeFiles(logininfoId);
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
}
