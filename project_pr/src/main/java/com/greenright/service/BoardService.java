package com.greenright.service;

import java.util.List;
import com.greenright.domain.Board;

public interface BoardService {
  List<Board> list() throws Exception;
  Board get(int no) throws Exception;
  void insert(Board board) throws Exception;
  void update(Board board) throws Exception;
  void delete(int no) throws Exception;
}
