package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 클라이언트로부터 요청을 받았을때 serverApp에서 해당 요청을 처리하기위해 
// 담당자를 호출하는 규칙입니다 . 
public interface Servlet {
    void service (String command,ObjectInputStream in , ObjectOutputStream out )throws  Exception;
  }

