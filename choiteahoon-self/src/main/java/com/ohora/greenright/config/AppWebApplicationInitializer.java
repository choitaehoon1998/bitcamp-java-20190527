package com.ohora.greenright.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppWebApplicationInitializer 
extends AbstractAnnotationConfigDispatcherServletInitializer{

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // TODO Auto-generated method stub
    return new Class<?>[] {Appconfig.class,mybatisconfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // TODO Auto-generated method stub
    return new Class<?> [] {Webconfig.class};
        }

  @Override
  protected String[] getServletMappings() {
    
    return new String [] {"/greenright/*"};
  }

}
