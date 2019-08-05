package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class CalcPlusCommand implements Command {
  Input input;

  public CalcPlusCommand(Input input) {
    this.input = input;
  }

  @Override
  public void execute() {
    int V1 = input.getIntValue("값1?");
    int V2 = input.getIntValue("값2?");
    System.out.println("합계는" + (V1 + V2) + "입니다");
  }

}
