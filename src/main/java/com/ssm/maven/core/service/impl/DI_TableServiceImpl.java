package com.ssm.maven.core.service.impl;

import com.ssm.maven.core.dao.DI_TableDao;
import com.ssm.maven.core.entity.DiTable;
import com.ssm.maven.core.service.DI_TableService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("di_tableService")
public class DI_TableServiceImpl implements DI_TableService {

    @Resource
    private DI_TableDao di_tableDao;

    @Override
    public List<DiTable> selectByScenic(DiTable diTable) throws Exception {
        return di_tableDao.selectByScenic(diTable);
    }
}
