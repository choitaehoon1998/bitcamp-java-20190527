package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.util.Input;
@Component


public class CalculatorCommand {

  @RequestMapping("/calc/plus") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void plus(BufferedReader in, PrintStream out) throws Exception {
    try {
      int v1 = Input.getIntValue(in, out, "값1?"); 
      int v2 = Input.getIntValue(in, out, "값2?");
      out.printf("%d + %d = %d\n ",v1,v2,(v1+v2));
    }catch(Exception e) {
      out.println();
    }
  }


  @RequestMapping("/calc/minus") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void minus(BufferedReader in, PrintStream out) throws Exception {
    try {
      int v1 = Input.getIntValue(in, out, "값1?"); 
      int v2 = Input.getIntValue(in, out, "값2?");
      out.printf("%d + %d = %d\n ",v1,v2,(v1-v2));
    }catch(Exception e) {
      out.println("");
    }
  }

}
// 실습과제 : 
// => 다음과같이 실행하도록 위 클래스를 완성하라
// 
// //
// >/calc/plus
//값 1: 10
// 값2 : 
