package com.greenright.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Board implements Serializable {

  private static final long serialVersionUID = 1L;
  private int no ;
  private String contents;
  private String title;
  private Date createdDate;
  private int viewCount;
  private int memberNo;
  
  private List<BoardPhoto> files;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public List<BoardPhoto> getFiles() {
    return files;
  }

  public void setFiles(List<BoardPhoto> files) {
    this.files = files;
  }

  @Override
  public String toString() {
    return "Board [no=" + no + ", contents=" + contents + ", title=" + title + ", createdDate="
        + createdDate + ", viewCount=" + viewCount + ", memberNo=" + memberNo + ", files=" + files
        + "]";
  }
  
  

  
  
  
}
