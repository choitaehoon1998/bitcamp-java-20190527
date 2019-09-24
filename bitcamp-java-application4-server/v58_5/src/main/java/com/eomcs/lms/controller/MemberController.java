package com.eomcs.lms.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@Controller
public class MemberController  {
  private String uploadDir;
  @Resource
  private MemberDao memberDao;

  @RequestMapping("/member/form")
  public String form() 
      throws Exception {
    return"/jsp/member/form.jsp";
  }
  
  @RequestMapping("/member/add")
  public String add(HttpServletRequest request, Member member , Part file ) 
      throws Exception {
    uploadDir = request.getServletContext().getRealPath("/upload/member");
 
      // 업로드 된 사진 파일 처리
      if (file != null && file.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        member.setPhoto(filename);
        file.write(uploadDir + "/" + filename);
      }

      memberDao.insert(member);
      return"redirect:list";
    }
  
  @RequestMapping("/member/delete")
  public String delete(int no ) 
      throws Exception{
      if (memberDao.delete(no) == 0) {
        throw new Exception("해당 데이터가 없습니다.");
      }
      return"redirect:list";
  }


  @RequestMapping("/member/detail")
  public String detail(Map<String,Object>model , int no) 
      throws Exception{


    Member member = memberDao.findBy(no);
    if (member == null) {
      throw new Exception("해당 번호의 데이터가 없습니다!");
    } 

    model.put("member", member);
    return"/jsp/member/detail.jsp";
  }
  
  @RequestMapping("/member/list")
  public String list(Map<String,Object>model) 
      throws Exception {
    
      List<Member> members = memberDao.findAll();
      
      model.put("members", members);
      return"/jsp/member/list.jsp";
      
  }
  
  @RequestMapping("/member/update")
  public String update(HttpServletRequest request,Member member, Part file) 
      throws Exception {
    
    uploadDir = request.getServletContext().getRealPath("/upload/member");
      // 업로드 된 사진 파일 처리
      Part photoPart = request.getPart("photo");
      if (photoPart != null && photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        member.setPhoto(filename);
        photoPart.write(uploadDir + "/" + filename);
      }
      
      memberDao.update(member);
      return"redirect:list";
      
  }
  @RequestMapping("/member/search")
  public String search(String keyword ,Map<String,Object>model) 
      throws Exception{
    
      List<Member> members = memberDao.findByKeyword(
          keyword);
      
      model.put("members", members);
      return"/jsp/member/search.jsp";
      
  }
  
}
