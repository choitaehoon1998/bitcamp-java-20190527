package com.eomcs.lms.util;

import java.sql.Date;
import java.util.Scanner;
// Input 이 사용하는 Scanner 객체를 각 input 마다 개별적으로 관리하기 위해 인스턴스 필드로 선언한다.

public class Input {
  private Scanner keyScan;

  public Input(Scanner keyScan) {
    // input 클래스에있는 메서드를 사용하는데 필요한 keyScan 변수를 초기화 시킨다 .
    // 단 인스턴스를 생성할때 반드시 Scanner 객체를 넘기도록 강제하기위해
    // 생성자에 파라미터 변수를 선언한다
    this.keyScan = keyScan;
  }
  // 기존의 스태틱 메서드를 인스턴스 메서드로 변환한 이유 
  // ==> 각 imput 객체마다 Scanner를 구분하여 다루기 위함이다 . 
  public int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  public String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

  public Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요.");
      }
    }
  }
}
