package com.greenright.web;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.greenright.domain.BoardPhoto;

@Component
public class BoardPhotoWriter {

  String uploadDir;
  public BoardPhotoWriter(ServletContext sc) {
    uploadDir =sc.getRealPath("/upload/photoboard");
  }
  public List<BoardPhoto> getPhotoFiles(
    MultipartFile[] filepath) throws Exception{
    List<BoardPhoto> photoFiles =new ArrayList<>();
    for(MultipartFile part : filepath) {
      if(part.getSize()==0)
        continue;
      
      String filename =UUID.randomUUID().toString();
      part.transferTo(Paths.get((uploadDir + "/" + filename)));
      
      BoardPhoto boardPhoto = new BoardPhoto();
      boardPhoto.setFilePath(filename);
      photoFiles.add(boardPhoto);
    }
    return photoFiles;
  }
  
}
