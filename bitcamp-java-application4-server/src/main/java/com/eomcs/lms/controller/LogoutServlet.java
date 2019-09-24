package com.eomcs.lms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component("/auth/logout")
public class LogoutServlet implements pageController {
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws Exception {
    
    request.getSession().invalidate();
    return"redirect:login";
  }
}
