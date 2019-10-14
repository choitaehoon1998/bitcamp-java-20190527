package com.ohora.greenright.dao;

import java.util.List;
import com.ohora.greenright.domain.Community;

public interface CommunityDao {
  int insert(Community community) throws Exception;
  List<Community> findAll() throws Exception;
  Community findBy(int no) throws Exception;
  int update(Community community)throws Exception;
  int delete(int no) throws Exception;
  int increaseViewCount(int no) throws Exception;
}
