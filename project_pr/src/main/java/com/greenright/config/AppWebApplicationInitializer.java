package com.greenright.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppWebApplicationInitializer 
extends AbstractAnnotationConfigDispatcherServletInitializer{

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {Appconfig.class,Databaseconfig.class,MybatIsconfig.class};
    
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/greenright/*"};
  }
  
  @Override
  protected String getServletName() {
    return "greenright";
  }

}
