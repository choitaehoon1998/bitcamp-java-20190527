package com.ohora.greenright.config;

import javax.sql.DataSource;
//import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@MapperScan("com.ohora.greenright.dao")
public class mybatisconfig {


  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource datasource,ApplicationContext appCtx)
          throws Exception{
    //LogFactory.useLog4J2Logging();
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setTypeAliasesPackage("com.ohora.greenright.domain");
    sqlSessionFactoryBean.setMapperLocations(appCtx.getResources
        ("classpath:com/ohora/greenright/mapper/*Mapper.xml"));


    return sqlSessionFactoryBean.getObject();

  }

}
