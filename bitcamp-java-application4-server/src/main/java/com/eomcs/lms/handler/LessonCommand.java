package com.eomcs.lms.handler;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ServletRequest;
import com.eomcs.util.ServletResponse;
@Component
public class LessonCommand  {

  private LessonDao lessonDao;

  public LessonCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }
  @RequestMapping("/lesson/form")
  public void form(ServletRequest request , ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 등록폼 </title></head>");
    out.println("<body><h1>수업 등록폼</h1>");
    out.println("<form action='/lesson/add'>");
    out.println("제목 : <textarea name='title' rows='2' cols ='50'> </textarea><br>");
    out.println("내용 : <textarea name='contents' rows='2' cols ='50'> </textarea><br>");
    out.println("시작일 : <textarea name='startDate' rows='2' cols ='50'> </textarea><br>");
    out.println("종료일 : <textarea name='endDate' rows='2' cols ='50'> </textarea><br>");
    out.println("일수업시간 : <textarea name='dayHours' rows='2' cols ='50'> </textarea><br>");
    out.println("총수업시간: <textarea name='totalHours' rows='2' cols ='50'> </textarea><br>");
    
    out.println("<button>등록</button> ");
    out.println("</form>");
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/add")
  public void add(ServletRequest request , ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 등록 </title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'>"
        + "</head>");
    out.println("<body><h1>수업 등록</h1>");
    try {
      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours").trim()));
      lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours").trim()));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate").trim()));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate").trim()));
      lessonDao.insert(lesson);
      
      out.println("<p>저장하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/delete")
  public void delete(ServletRequest request , ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 삭제 </title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'>"
        + "</head>");
    out.println("<body><h1>수업 삭제</h1>");
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if (lessonDao.delete(no) > 0) {
        out.println("<p>데이터를 삭제하였습니다.</p>");
      } else {
        out.println("<p>해당 데이터가 없습니다.</p>");
      }

    } catch (Exception e) {
      out.println("<p>데이터 삭제에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/detail")
  public void detail(ServletRequest request , ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 상세정보 </title></head>");
    out.println("<body><h1>게시물 상세></h1>");

    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Integer.parseInt(request.getParameter("no"));

      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
        return;
      }else {
        out.println("<form action='/lesson/update'>");
        out.printf("번호          : <input type='text' name='no' value='%d' readonly><br>",lesson.getNo());
        out.printf("내용          : <textarea name='contents' rows='5' "
            + "cols ='50'>%s </textarea><br>\n",
            lesson.getContents());
        out.printf("제목          : <textarea name='title' rows='5' cols ='10'>%s </textarea><br>\n ",lesson.getTitle());
        out.printf("시작일       : <textarea name='startDate' rows='5' cols ='10'>%s </textarea><br>\n ",lesson.getStartDate());
        out.printf("종료일       : <textarea name='endDate' rows='5' cols ='10'>%s </textarea><br>\n ",lesson.getEndDate());
        out.printf("일수업시간 : <textarea name='dayHours' rows='5' cols ='10'>%s </textarea><br>\n ",lesson.getDayHours());
        out.printf("총수업시간 : <textarea name='totalHours' rows='5' cols ='10'>%s </textarea><br>\n ",lesson.getTotalHours());
        out.println("<button>변경</button> ");
        out.printf("<a href='/lesson/delete?no=%d'>삭제</a>\n",lesson.getNo());
        out.println("</form>");
      }

    } catch (Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/list")
  public void list(ServletRequest request , ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업목록 </title>"
        + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>"
        + "</head>");
    out.println("<body><h1>수업 목록></h1>");
    out.println("<a href='/lesson/form'>새글</a><br>");
    try {

      out.println("<table class='table table-hover'>");
      out.println("<tr><th>번호</th><th>제목</th><th>시작일</th><th>종료일"
          + "</th><th>일수업시간</th><th>총수업시간</th></tr>");
      List<Lesson> lessons = lessonDao.findAll();
      for (Lesson lesson : lessons) {
        out.printf("<tr><td>%s</td>"
            + "<td><a href='/lesson/detail?no=%d'>%s</a></td>"
            + "<td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", 
            lesson.getNo(),
            lesson.getNo(),
            lesson.getTitle(),
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getDayHours(),
            lesson.getTotalHours());
      }

    } catch (Exception e) {
      out.println("<p>데이터 목록 조회에 실패했습니다!<p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }

  @RequestMapping("/lesson/update")
  public void update(ServletRequest request , ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'>"
        + "</head>");
    out.println("<body><h1>수업 변경</h1>");
    try {

      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours").trim()));
      lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours").trim()));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate").trim()));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate").trim()));
      lesson.setNo(Integer.parseInt(request.getParameter("no").trim()));
      lessonDao.update(lesson);
      out.println("<p>데이터를 변경하였습니다.</p>");

    } catch (Exception e) {
      out.println("<p>데이터 변경에 실패했습니다!<p>");
      throw new RuntimeException(e);
    }
    out.println("</body></html>");
  }
}

