// v35_2 : serverstop 명령어에 대해 jvm 강제종료하기

package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp {

  ArrayList<ServletContextListener> listeners = new ArrayList<>();
  int port;

  HashMap<String, Object> servletContext = new HashMap<>();

  ExecutorService executorService=  Executors.newCachedThreadPool();



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



        // 스레드풀을 사용할때는 직접 스레드를 만들지않는다
        // 단지 스레드풀에게 "스레드가 실행할 코드(runnable 구현체)"를 제출한다
        // => 스레드풀은 남은 스레드가없으면 새로 만들어 해당 코드RequestHandler를 실행할것이다. 
        // => 남아있는 스레드가있다면  그스레드를 이용하여 해당코드RequestHandler를 실행할것이다. 
        executorService.submit(new RequestHandler(socket));
        
      } // while


    } catch (Exception e) {
      e.printStackTrace();
    }

    
    
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
  
  // serverstop 명령처리 
  private void stop() {
    // 서버가 종료될 때 관찰자(observer)에게 보고한다.
    for (ServletContextListener listener : listeners) {
      listener.contextDestroyed(servletContext);
    }
    

    //스레드풀에게 동작을 멈추라고 알려준다 
    // => 그러면 스레드풀은 작업중인 모든 스레드가 작업이 완료될때까지 기달렸다가 
    //    스레드풀의작업을 종료한다 . 
    executorService.shutdown();
    System.out.println("서버 종료!");
    System.exit(0);//단점 ! 현재 실행중인 스레드까지 강제종료시킨다 .
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
          stop();
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




