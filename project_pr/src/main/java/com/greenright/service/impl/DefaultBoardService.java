package com.greenright.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.greenright.dao.BoardDao;
import com.greenright.dao.BoardPhotoDao;
import com.greenright.domain.Board;
import com.greenright.domain.BoardPhoto;
import com.greenright.service.BoardService;
@Service
public class DefaultBoardService implements BoardService{
  @Resource
  private BoardDao boardDao;
  @Resource
  private BoardPhotoDao boardPhotoDao;
  
  
  @Override
  public List<Board> list() throws Exception {
    return boardDao.findAll();
  }

  @Override
  public Board get(int no) throws Exception {
    Board board = boardDao.findWithFilesBy(no);
    if(board ==null) {
      throw new Exception("해당 번호의 데이터가없습니다.");
    }
    boardDao.increaseViewCount(no);
    return board;
  }
  @Transactional
  @Override
  public void insert(Board board) throws Exception {
    
    if(board.getFiles().size()==0) {
      throw new Exception("사진파일없음");
    }
    boardDao.insert(board);
    
    for(BoardPhoto photo :board.getFiles() ) {
      photo.setBoardNo(board.getNo());
      boardPhotoDao.insert(photo);
    }
  }
  @Transactional
  @Override
  public void update(Board board) throws Exception {
    if(board.getFiles().size()==0) {
      throw new Exception("사진 파일 없음!");
    }
    boardDao.update(board);
    boardPhotoDao.deleteAll(board.getNo());
    for(BoardPhoto photo :board.getFiles() ) {
      photo.setBoardNo(board.getNo());
      boardPhotoDao.insert(photo);
    }
  }
  @Transactional
  @Override
  public void delete(int no) throws Exception {
    if(boardDao.findBy(no)==null) {
      throw new Exception("해당데이터가없습니다");
    }
    boardPhotoDao.deleteAll(no);
    boardDao.delete(no);
  }

}
