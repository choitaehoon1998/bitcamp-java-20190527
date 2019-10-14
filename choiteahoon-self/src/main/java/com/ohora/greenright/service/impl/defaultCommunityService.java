package com.ohora.greenright.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ohora.greenright.dao.CommunityDao;
import com.ohora.greenright.domain.Community;
import com.ohora.greenright.service.CommunityService;

@Service
public class defaultCommunityService implements CommunityService{

  @Resource
  private CommunityDao communityDao;
  
  
  @Override
  public void insert(Community community) throws Exception {
    communityDao.insert(community);
  }

  @Override
  public void delete(int no) throws Exception {
    if(communityDao.delete(no)==0) {
      throw new Exception("해당데이터가없습니다.");
    }
  }

  @Override
  public Community get(int no) throws Exception {
    Community community = communityDao.findBy(no);
    if(community ==null) {
      throw new Exception("해당번호의 데이터가없습니다.");
    }
    communityDao.increaseViewCount(no);
    return community;
  }

  @Override
  public List<Community> list() throws Exception {
    return communityDao.findAll();
  }

  @Override
  public void update(Community community) throws Exception {
    communityDao.update(community);
  }

}
