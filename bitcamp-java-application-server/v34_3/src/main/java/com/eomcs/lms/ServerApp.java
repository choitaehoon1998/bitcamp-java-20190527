// v34_3: Runnable 인터페이스를 사용하여 간접적으로 Thread를 실행하기 

package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp {
  public static boolean isStopping = false;

  ArrayList<ServletContextListener> listeners = new ArrayList<>();
  int port;

  HashMap<String, Object> servletContext = new HashMap<>();

  public ServerApp(int port) {
    this.port = port;
  }

  public void execute() {
    System.out.println("[수업관리시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 시작!");

      for (ServletContextListener listener : listeners) {
        listener.contextInitialized(servletContext);
      }

      while (true) {
        System.out.println("클라이언트 요청을 기다리는중....");
        //클라이언트 요청이 들어오면 클라이언트와 통신할때 사용할 소켓을 생성한다
        Socket socket = serverSocket.accept();




        /*
        //그리고 소켓을 이요하여 클라이언ㅌ트 요청을 처리할 객체를 생성한다
        RequestHandler requestHandler = new RequestHandler(socket,servletContext);
        // 단 RequestHandler 의 작업은 별도의 실행흐름으로 분리하여 실행시킨다
        // => RequestHandler 는 별도의 실행흐름으로 분리될수있는 Thread기능을 상속받았다
        requestHandler.start();
        new RequestHandler(socket).start();
         */
        // 위의 두문장을 다음 한문장으로 처리한다 .
        new Thread(new RequestHandler(socket)).start();

        if(isStopping)
          break;
      } // while

      // 서버가 종료될 때 관찰자(observer)에게 보고한다.
      for (ServletContextListener listener : listeners) {
        listener.contextDestroyed(servletContext);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }


    System.out.println("서버 종료!");
  }

  private Servlet findServlet(String command) {
    Set<String> keys = servletContext.keySet();
    for (String key : keys) {
      if (command.startsWith(key)) {
        return (Servlet) servletContext.get(key);
      }
    }
    return null;
  }


  public void addServletContextListener(ServletContextListener listener) {
    listeners.add(listener);
  }
  // Thread 를 상속받아 직접 스레드 역활을 하는대신에 
  // Thread에서 실행할 코드를 정의한다 . 
  private class RequestHandler implements Runnable {

    Socket socket;

    public RequestHandler(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run(){

      try (Socket clientSocket = this.socket;
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

        System.out.println("클라이언트와 연결되었음.");

        String command = in.readUTF();
        System.out.println(command + " 요청 처리중...");

        Servlet servlet = null;

        if(command.equals("serverstop")) {
          isStopping =true;
          return;

        } else if ((servlet = findServlet(command)) != null) {
          servlet.service(command, in, out);

        } else {
          out.writeUTF("fail");
          out.writeUTF("지원하지 않는 명령입니다.");

        }
        out.flush();
        System.out.println("클라이언트에게 응답 완료!");
      }catch(Exception e) {
        System.out.println("클라이언트와의 통신오류 "+e.getMessage());
      }

      System.out.println("클라이언트와 연결을 끊었음.");
    }


  }



  public static void main(String[] args) {

    ServerApp server = new ServerApp(8888);

    // 서버의 시작과 종료 상태를 보고 받을 객체를 등록한다.
    // => 보고를 받는 객체는 ServletContextListener 규칙에 따라 만든 클래스여야 한다.
    server.addServletContextListener(new AppInitListener());

    server.execute();
  }
}




