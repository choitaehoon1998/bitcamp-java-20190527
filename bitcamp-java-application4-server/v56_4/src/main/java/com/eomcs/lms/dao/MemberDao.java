package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Member;
//DAO 사용규칙을 정의한다 
public interface MemberDao {
  int insert(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  Member findBy(int no) throws Exception;
  List<Member> findByKeyword(String keyword) throws Exception;
  int update(Member member) throws Exception;
  int delete(int no) throws Exception;
  Member findByEmailPassword(Map<String,Object> params)throws Exception;
  
}
