package com.greenright.domain;

import java.io.Serializable;

public class BoardPhoto implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private int no;
  private int boardNo;
  private String filePath;
  
  public int getBoardNo() {
    return boardNo;
  }
  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }
  public String getFilePath() {
    return filePath;
  }
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  
  @Override
  public String toString() {
    return "BoardPhoto [boardNo=" + boardNo + ", filePath=" + filePath + ", no=" + no + "]";
  }
  
  
  
}
