package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.DiTable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DI_TableDao {

    public List<DiTable> selectByScenic(DiTable diTable) throws Exception;
}
