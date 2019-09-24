package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.eomcs.util.RequestMappingHandlerMapping;
import com.eomcs.util.RequestMappingHandlerMapping.RequestHandler;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LogManager.getLogger(DispatcherServlet.class);

  private ApplicationContext iocContainer;
  private RequestMappingHandlerMapping handlerMapping;
  @Override
  public void init() throws ServletException {
    iocContainer = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    handlerMapping = new RequestMappingHandlerMapping(iocContainer);
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

    String servletPath = request.getServletPath();
    String pathInfo = request.getPathInfo();
    log.debug(String.format("요청 => servletPath: %s, pathInfo: %s", 
        servletPath, pathInfo));

    try {
      // 클라이언트 요청을 처리할 request Handler를 구현한 객체를 찾는다.
      RequestHandler requestHandler = handlerMapping.getRequestHandler(pathInfo);
    // 페이지 컨트롤러 에서 @Request Mapping 이 붙은 메서드를 찾는다
      if(requestHandler ==null) {
        throw new Exception(pathInfo + "요청을 처리할수없습니다");
      }


      // request handler 를 실행한다 .
      String viewUrl = (String) requestHandler.invoke(request,response);
      
      // 응답 콘텐트의 MIME 타입과 문자집합을 설정한다.
      String contentType = (String) request.getAttribute("contentType");
      if (contentType != null) {
        response.setContentType(contentType);
      } else {
        response.setContentType("text/html;charset=UTF-8");
      }

      // 페이지 컨트롤러 작업을 수행한 후 리턴 URL에 따라 JSP를 실행한다.
      if (viewUrl != null) {
        if (viewUrl.startsWith("redirect:")) {
          response.sendRedirect(viewUrl.substring(9)); // "redirect:list"
        } else {
          request.getRequestDispatcher(viewUrl).include(request, response);
        }
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
    }
  }
 
}






