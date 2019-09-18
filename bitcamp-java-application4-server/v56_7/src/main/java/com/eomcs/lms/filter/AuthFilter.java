package com.eomcs.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//역할 : 
// => 로그인 사용자만  등록 변경 수정 삭제 를 수행할수있게한다  
public class AuthFilter implements Filter{
  String [] path;
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    //Web.Xml 에 등록된 path value 를 가져온다 . 
    path = filterConfig.getInitParameter("path").split(",");

  }

  @Override
  public void doFilter(ServletRequest request,


      ServletResponse response,
      FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest httpReq = (HttpServletRequest) request;
    HttpServletResponse httpResp = (HttpServletResponse) response;



    String ServletPath = httpReq.getServletPath();

    for(String p: path) {
      //web.Xml 에 지정된 경로라면 로그인여부를 검사한다 . 
      if(ServletPath.endsWith(p)) {
        //로그인 하지않았다면 로그인 폼으로 보낸다 .
        if( httpReq.getSession().getAttribute("loginUser") ==null) {
          httpResp.sendRedirect("/auth/login"); 
          return;
        }else {
          //로그인 했다면반복문을 멈추고 다음목적지로 보낸다 . 
          break;
        }
      }
    }
    // 로그인 했다면 원래 의 목적지로 보낸다 
    chain.doFilter(request, response);

  }
}
