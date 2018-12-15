package com.cs.springbootestfour.dao;

import com.cs.springbootestfour.pojo.Kaoshi;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
public interface KaoshiDao extends BaseMapper<Kaoshi> {

    @SqlStatement(params = "name")
    Kaoshi selectKaoshiByKsname(String name);

}
