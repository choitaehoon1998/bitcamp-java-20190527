package com.eomcs.lms.listener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//역활: 
// => 웹 애플리케이션이 시작할때 보고를 받는다 . 
// => Spring IoC 컨테이너를 준비한다
// => ServletCotnext 보관소에 IoC 컨테이너를 저장한다 
public class ContextLoaderListener implements ServletContextListener {
  private static final Logger logger  = 
      LogManager.getLogger(ContextLoaderListener.class);



  @Override
  public void contextInitialized(ServletContextEvent sce) {
    //웹 애플리케이션 이 시작될때 서블릿 컨테이너가 호출하는 메서드 
    ServletContext sc = sce.getServletContext();
    ApplicationContext appCtx =null;
    //Spring Ioc 컨테이너의 클래스 이름을 가져오기 
    String contextClass = sc.getInitParameter("contextClass");
    if(contextClass !=null){
      try {
        //web.xml 에 설정한  ioc 컨테이너의 클래스를 로딩한다 . 
        Class<?> clazz = Class.forName(contextClass);

        //해당클래스의 생성자웅에서 자바패키지 이름을 문자열로 받는 생성자를 꺼낸다 
        Constructor<?> constructor = clazz.getConstructor(String[].class);

        //web.xml 에서 Ioc 컨테이너가 사용할 패키지 이름을 가져온다
        String basePackageName = sc.getInitParameter("contextConfigLocation");

        // 생성자를 이용하여 Ioc 컨테이너 객체를 만든다 
        appCtx =  (ApplicationContext)constructor.newInstance(
            (Object) new String [] {basePackageName});
      } catch (Exception e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));

        logger.error(out.toString());
      }
    } else {
      // 파라미터로 전달한 패키지를 뒤져서 
      // @Configuration 애노테이션이 붙은 클래스를 찾는다 
      // 이 애노테이션이 붙은 클래스가 Java Config 클래스이다
      // Ioc 컨테이너는 이 Java Config에 설정된 대로 객체를 준비한다 .  
      appCtx = new AnnotationConfigApplicationContext("com.eomcs.lms.config");

    }

    //Spring Ioc 컨테이너를 서블릿 에서 사용할수있도록 ServletCotnext 보관소에 저장한다 . 
    sc.setAttribute("iocContainer", appCtx);

    String[] beanNames = appCtx.getBeanDefinitionNames();
    logger.debug("[Spring IoC 컨테이너 객체들]------------");
    for (String beanName : beanNames) {
      logger.debug(String.format("%s(%s)",
          appCtx.getBean(beanName).getClass().getSimpleName(),
          beanName));
    }
    logger.debug("------------------------------------");

  }

}
