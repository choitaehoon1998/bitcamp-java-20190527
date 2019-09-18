// v56_1 : 서블릿을 이용하여 클라이언트 요청 처리하기  
package com.eomcs.lms;

import java.lang.reflect.Constructor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomcs.util.RequestMappingHandlerMapping;
// 역할 : 
// => 다른 서블릿이 사용할 객체를 준비한다 . 
// => Spring ioc 컨테이너를 준비하여 Servletcontext 보관소에 저장한다 .
// => 이 서블릿은 클라이언트가 직접 실행하는 서블릿이 아니기때문에 
//    서버에 배치할때 URl 을 지정하지않는다 
//    또한 Service() 메서드를 오버라이딩 하지않는다
public class AppInitServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  private static final Logger logger  = LogManager.getLogger(AppInitServlet.class);

  ApplicationContext appCtx;
  RequestMappingHandlerMapping handlerMapping;
  int state;
  
  @Override
  public void init() throws ServletException {
    //Spring Ioc 컨테이너의 클래스 이름을 가져오기 
    String contextClass = getInitParameter("contextClass");
    if(contextClass !=null){
      try {
        //web.xml 에 설정한  ioc 컨테이너의 클래스를 로딩한다 . 
        Class<?> clazz = Class.forName(contextClass);
        
        //해당클래스의 생성자웅에서 자바패키지 이름을 문자열로 받는 생성자를 꺼낸다 
        Constructor<?> constructor = clazz.getConstructor(String[].class);
        
        //web.xml 에서 Ioc 컨테이너가 사용할 패키지 이름을 가져온다
        String basePackageName = getInitParameter("contextConfigLocation");
        
        // 생성자를 이용하여 Ioc 컨테이너 객체를 만든다 
        appCtx =  (ApplicationContext)constructor.newInstance(
            (Object) new String [] {basePackageName});
      } catch (Exception e) {
        throw new ServletException(e);
      }
    } else {
      // 파라미터로 전달한 패키지를 뒤져서 
      // @Configuration 애노테이션이 붙은 클래스를 찾는다 
      // 이 애노테이션이 붙은 클래스가 Java Config 클래스이다
      // Ioc 컨테이너는 이 Java Config에 설정된 대로 객체를 준비한다 .  
      appCtx = new AnnotationConfigApplicationContext("com.eomcs.lms.config");
      
    }

    //Spring Ioc 컨테이너를 서블릿 에서 사용할수있도록 ServletCotnext 보관소에 저장한다 . 
    getServletContext().setAttribute("iocContainer", appCtx);

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


