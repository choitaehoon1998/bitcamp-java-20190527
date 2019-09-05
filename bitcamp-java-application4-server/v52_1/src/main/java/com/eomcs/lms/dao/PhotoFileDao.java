package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoFile;
//DAO 사용규칙을 정의한다 
public interface PhotoFileDao {
  int insert(PhotoFile photoFile) throws Exception;
  List<PhotoFile> findAll(int BoardNo) throws Exception;
  int deleteAll(int BoardNo) throws Exception;

}
