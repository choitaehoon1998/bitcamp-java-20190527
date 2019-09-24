package com.eomcs.lms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 역활 : 
// => 페이지 컨트롤러 가 반드시 구현해야 하는 규칙
// => 프론트 컨트롤러는 이 규칙에  따라 메소드를 호출한다 .
//
public interface pageController {
  public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception;


}
