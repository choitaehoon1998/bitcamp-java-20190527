package com.greenright.domain;

import java.io.Serializable;

public class Recommend implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private int BoardNo;
  private int memberNo;
  public int getBoardNo() {
    return BoardNo;
  }
  public void setBoardNo(int boardNo) {
    BoardNo = boardNo;
  }
  public int getMemberNo() {
    return memberNo;
  }
  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }
  @Override
  public String toString() {
    return "Recommend [BoardNo=" + BoardNo + ", memberNo=" + memberNo + "]";
  }
 
  
}
