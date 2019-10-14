package com.ohora.greenright.service;

import java.util.List;
import com.ohora.greenright.domain.Community;

public interface CommunityService {
  List<Community> list() throws Exception;
  Community get (int no) throws Exception;
  void insert(Community community) throws Exception;
  void update(Community community) throws Exception;
  void delete(int no)throws Exception;
}
