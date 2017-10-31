package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.DiTable;

import java.util.List;

public interface DI_TableService {

    public List<DiTable> selectByScenic(DiTable diTable) throws Exception;
}
