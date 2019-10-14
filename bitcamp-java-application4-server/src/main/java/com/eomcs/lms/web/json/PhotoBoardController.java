package com.eomcs.lms.web.json;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.lms.web.PhotoFileWriter;

@RequestMapping("/json/photoboard")
@RestController("json.PhotoBoardController")
public class PhotoBoardController {

  @Resource private PhotoBoardService photoBoardService;
  @Resource private PhotoFileWriter photoFileWriter ;

  @RequestMapping("add")
  public JsonResult add(
      HttpServletRequest request, 
      PhotoBoard photoBoard,
      MultipartFile[] filePath) throws Exception {
    try {
      photoBoard.setFiles(photoFileWriter.getPhotoFiles(filePath));
      // 서비스 컴포넌트를 통해 데이터를 저장한다 
      photoBoardService.insert(photoBoard);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) { 
      return  new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());

    }
  }

  @RequestMapping("delete")
  public JsonResult delete(int no) 
      throws Exception {

    try {
      photoBoardService.delete(no);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new  JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("detail")
  public JsonResult detail(int no) 
      throws Exception {
    try {
      PhotoBoard photoBoard = photoBoardService.get(no);
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(photoBoard);
    }catch(Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("list")
  public JsonResult list() 
      throws Exception {
    try {
      List<PhotoBoard> photoBoards = photoBoardService.list();
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(photoBoards);
    }catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }
  @RequestMapping("update")
  public JsonResult update(
      HttpServletRequest request, 
      PhotoBoard photoBoard,
      MultipartFile[] filePath) throws Exception {
    try {
      photoBoard.setFiles(photoFileWriter.getPhotoFiles(filePath));
      // 서비스 컴포넌트를 통해 데이터를 저장한다 
      photoBoardService.update(photoBoard);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) { 
      return  new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());

    }
  }
 







}
