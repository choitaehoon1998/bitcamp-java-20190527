// client v38_3 : 사진게시판 다루기 + 첨부파일 다루기 + 트렌젝션 적용 
package com.eomcs.lms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoBoardDaoImpl;
import com.eomcs.lms.dao.mariadb.PhotoFileDaoImpl;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberSearchCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
import com.eomcs.lms.handler.PhotoBoardAddCommand;
import com.eomcs.lms.handler.PhotoBoardDeleteCommand;
import com.eomcs.lms.handler.PhotoBoardDetailCommand;
import com.eomcs.lms.handler.PhotoBoardListCommand;
import com.eomcs.lms.handler.PhotoBoardUpdateCommand;

public class App {

  private static final int CONTINUE = 1;
  private static final int STOP = 0;
  
  public static Connection con;
  HashMap<String, Command> commandMap = new HashMap<>();
  int state;

  //스레드풀 
  ExecutorService executorService=  Executors.newCachedThreadPool();

  public App() throws Exception {
    //처음에는 클라이언트 요청을 처리해야하는 상태로 설정한다 . 
    state = CONTINUE;
    try {
      // Dao 가 사용할 Connection 객체 준비하기
      con = DriverManager
          .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");

      // Command 객체가 사용할 데이터 처리 객체를 준비한다.
      BoardDao boardDao = new BoardDaoImpl(con);
      MemberDao memberDao = new MemberDaoImpl(con);
      LessonDao lessonDao = new LessonDaoImpl(con);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(con);
      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(con);
      
      // 클라이언트 명령을 처리할 커멘드 객체를 준비한다
      commandMap.put("/lesson/add", new LessonAddCommand(lessonDao));
      commandMap.put("/lesson/delete", new LessonDeleteCommand(lessonDao));
      commandMap.put("/lesson/detail", new LessonDetailCommand(lessonDao));
      commandMap.put("/lesson/list", new LessonListCommand(lessonDao));
      commandMap.put("/lesson/update", new LessonUpdateCommand(lessonDao));

      commandMap.put("/member/add", new MemberAddCommand(memberDao));
      commandMap.put("/member/delete", new MemberDeleteCommand(memberDao));
      commandMap.put("/member/detail", new MemberDetailCommand(memberDao));
      commandMap.put("/member/list", new MemberListCommand(memberDao));
      commandMap.put("/member/update", new MemberUpdateCommand(memberDao));
      commandMap.put("/member/search", new MemberSearchCommand(memberDao));

      commandMap.put("/board/add", new BoardAddCommand(boardDao));
      commandMap.put("/board/delete", new BoardDeleteCommand(boardDao));
      commandMap.put("/board/detail", new BoardDetailCommand(boardDao));
      commandMap.put("/board/list", new BoardListCommand(boardDao));
      commandMap.put("/board/update", new BoardUpdateCommand(boardDao));

      commandMap.put("/photoboard/list", new PhotoBoardListCommand(photoBoardDao));
      commandMap.put("/photoboard/add", new PhotoBoardAddCommand(photoBoardDao,photoFileDao));
      commandMap.put("/photoboard/delete", new PhotoBoardDeleteCommand(photoBoardDao,photoFileDao));
      commandMap.put("/photoboard/update", new PhotoBoardUpdateCommand(photoBoardDao,photoFileDao));
      commandMap.put("/photoboard/detail", new PhotoBoardDetailCommand(photoBoardDao,photoFileDao));
      
    } catch (Exception e) {
      System.out.println("DBMS에  연결할 수 없습니다!");
      throw e;
    }
  }

  @SuppressWarnings("static-access")
  private void service() {
    try (ServerSocket serverSocket = new ServerSocket(8888);) {
      System.out.println("어플리케이션 서버가 시작되었음 ");


      while (true) {
        //클라이언트가 접속하면 작업을 수행할 runnable 객체를 만들어 스레드풀에 맡긴다 . 
        executorService.submit(new CommandProcessor(serverSocket.accept()));
        //한클라이언트가 serverstop 명령을 보내면 종료상태로 설정되고
        // 다음요청을 처리할때 즉시 실행을 멈춘다 . 

        if(state==STOP)
          break;
      }

      //스레드풀에게 실행 종료를 요청한다 . 
      //스레드풀은 자신이 관리하는 스레드들이 실행이 종료되었는지 감시한다. 
      executorService.shutdown();

      //자신이 관리하는 모든 쓰레드들이 종료되었는지 매 0.5초마다 검사한다 .
      //스레드풀의 모든스레드가 실행을 종료했으면 즉시 main스레드를 종료한다 
      while(!executorService.isTerminated()) {
        Thread.currentThread().sleep(500);
      }
      System.out.println("애플리케이션 서버를 종료함 ");
    }catch(Exception e) {
      System.out.println("통신 오류 ");
      e.printStackTrace();
    }


    // DBMS 와의 연결을 끊는다.
    try {
      con.close();
    } catch (Exception e) {
      //연결끊을때 발생되는 예외는 무시한다 
    }
  }


  class CommandProcessor implements Runnable {

    Socket socket;

    public CommandProcessor(Socket socket) {
      this.socket=socket;
    }

    @Override
    public void run() {
      try (Socket socket = this.socket;
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintStream out = new PrintStream(socket.getOutputStream())) {



        System.out.println("클라이언트와 연결됨 ");


        // 클라이언트가 보낸 명령을 읽는다
        String request = in.readLine();
        if (request.equals("quit")) {
          out.println("Good bye!");
        }else if(request.equals("serverstop")) {
          state =STOP;
          out.println("Good bye!");
          // dummyRequest();

        }else {
          // non-static 중첩클래스는 바깥 클래스의 인스턴스 멤버를 사용할수있따 
          Command command = commandMap.get(request);
          if (command == null) {
            out.println("해당명령을 처리할수없습니다");
          }else {
            command.execute(in, out );
          }
        }
        out.println("!end!");
        out.flush();

        System.out.println("클라이언트와 연결 끊음 ");


      } catch (Exception e) {
        System.out.println("클라이언트와 통신오류 ");
      }
      //다른 클라이언트의 요청을 계속 처리할지 말지 상태값으로 알려준다 .
    }
  }
  //  private void dummyRequest() {
  //    // 클라이언트에서 serverStop 명령을 보내면 ,
  //    // 서버의 state의 값이 stop으로 바뀐다 . 
  //    // 이 State 상태를 즉시 인식할 수 있도록 가상의 클라이언트가 되어 요청을 보낸다  
  //    try (Socket socket = new Socket("localhost",8888);
  //        PrintStream out = new PrintStream(socket.getOutputStream());
  //        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
  //      out.println("quit");
  //      out.flush();
  //
  //      while(true) {
  //        if(in.readLine().equals("!end!")) 
  //          break;
  //
  //      }
  //    }catch(Exception e) {
  //      //예외는 무시한다 . 
  //    }
  //  }

  public static void main(String[] args) {
    try {
      App app = new App();
      app.service();
    } catch (Exception e) {
      System.out.println("시스템 실행중 오류 발생 ");
      e.printStackTrace();
    }
  }
}


