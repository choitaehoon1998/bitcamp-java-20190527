// v55_1 : Servlet 컨테이너 도입하기 
package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.util.RequestMappingHandlerMapping;
import com.eomcs.util.RequestMappingHandlerMapping.RequestHandler;

@WebServlet("/*")
public class App implements Servlet{

  private static final Logger logger  = LogManager.getLogger(App.class);
  ServletConfig config;
  ApplicationContext appCtx;
  RequestMappingHandlerMapping handlerMapping;
  int state;
  @Override
  public void init(ServletConfig config) throws ServletException {

    this.config =config;

    //톰캣서버가 이객체를 사용하기전에 이 객체가 작업을 수행하는데 필요한 자원들을 
    //준비할수있도록 이메서드를 호출한다 . 
    appCtx = new AnnotationConfigApplicationContext(AppConfig.class);

    // Spring IoC 컨테이너에 들어 있는(Spring IoC 컨테이너가 생성한) 객체 알아내기
    String[] beanNames = appCtx.getBeanDefinitionNames();
    logger.debug("[Spring IoC 컨테이너 객체들]------------");
    for (String beanName : beanNames) {
      logger.debug(String.format("%s(%s)",
          appCtx.getBean(beanName).getClass().getSimpleName(),
          beanName));
    }
    logger.debug("------------------------------------");

    handlerMapping = createRequestMappingHandlerMapping();
  }
  @Override
  public void service(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response)
      throws ServletException, IOException {
    //클라이언트 요청이 들어올때 마다  톰캣서버가 호출하는 메서드이다 . 
    // 클라이언트가 요청한 정보를 자세하게 뽑기위해 파라미터 객체를 원래의 타입으로 형변환 한다 . 
    HttpServletRequest httpReq =(HttpServletRequest) request;
    String command = httpReq.getPathInfo();
    logger.info(command);
    logger.info(httpReq.getQueryString());

    try { RequestHandler requestHandler = 
        handlerMapping.getRequestHandler(command);
response.setContentType("text/html;charset=UTF-8");
    if (requestHandler != null) {
      //클라이언트 요청 처리하기위해 메서드를 호출한다 .
      requestHandler.method.invoke(requestHandler.bean,
          request ,
          response);
    } else {
      PrintWriter out = response.getWriter();
      out.println(
          "<html><body>"
              + "<h1>해당 명령을 처리하는 커맨드를 찾을 수 없습니다</h1>"
              + "</body></html>"
          );
      logger.info("실패! - "+command);    
    }



    } catch (Exception e) {
      PrintWriter out = response.getWriter();
      out.println(
          "<html><body><h1>요청 처리중 오류발생!</h1></body></html>");
      logger.info("클라이언트 요청처리중 오류발생!");

      StringWriter out2 = new StringWriter();
      e.printStackTrace(new PrintWriter(out2));
      logger.debug(out2.toString());

    }

  }
  public void destroy() {
    //톰캣 서버가 종료되기전에 이객체가 준비한 자원들을 해제할수있또록 이메서드를 호출한다
  }
  @Override
  public String getServletInfo() {
    //톰캣서버가 관리자페이지에 애플리케이션에대해 간단한 소개를 출력하기위해
    // 이메서드를 호출한다 즉이메서드의 리턴값을 관리자 페이지에 출력한다
    return "수업관리 시스템";
  }
  @Override
  public ServletConfig getServletConfig() {
    //이객체를 실행하면서 이객체의 실행 환경정보를 알고싶을때 이메서드를 호출한다 
    //리턴값은 init()에서 받은 파리미터값이다
    //따라서 init()메서드에서 파라미터값을 잘보관해둬야한다 

    return this.config;
  }
  private RequestMappingHandlerMapping createRequestMappingHandlerMapping() {

    RequestMappingHandlerMapping mapping = 
        new RequestMappingHandlerMapping();

    // 객체풀에서 @Component 애노테이션이 붙은 객체 목록을 꺼낸다.
    Map<String,Object> components = appCtx.getBeansWithAnnotation(Component.class);

    logger.trace("[요청 핸들러] =======================================");

    // 객체 안에 선언된 메서드 중에서 @RequestMapping이 붙은 메서드를 찾아낸다.
    Collection<Object> objList = components.values();
    objList.forEach(obj -> {

      Method[] methods = null;
      if(AopUtils.isAopProxy(obj)) {//원본이아니라 AopProxy객체일경우 
        try {
          //프록시 객체의 클래스가아니라 원본객체의 클래스정보를 가져온다 . 
          Class<?> originClass = 
              (Class<?>) obj.getClass().getMethod("getTargetClass").invoke(obj);
          methods = originClass.getMethods();
        }catch(Exception e) {
          e.printStackTrace();
        }
      }else {//원본객체일경우 원본객체의 클래스로부터 메서드 목록을 가져온다 
        // => 객체에서 메서드 정보를 추출한다.
        methods = obj.getClass().getMethods();
      }
      for (Method m : methods) {
        RequestMapping anno = m.getAnnotation(RequestMapping.class);
        if (anno == null)
          continue;
        // @RequestMapping 이 붙은 메서드를 찾으면 mapping 객체에 보관한다.
        mapping.addRequestHandler(anno.value()[0], obj, m);
        logger.trace(String.format("%s ==> %s.%s()", anno.value()[0],
            obj.getClass().getSimpleName(),
            m.getName()));
      }
    });

    return mapping;
  }






}











