package ch23.j.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
  // 연결된 클라이언트의 출력스트림을 보관하는 객체
  ArrayList<PrintStream> outPutStreams = new ArrayList<>();
  int port;

  public ChatServer(int port) {
    this.port = port;
  }

  public void service() {
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("채팅서버 시작");
      while (true) {
        new Thread(new ChatAgent(serverSocket.accept())).start();
        System.out.println("채팅서버와 연결되엇음");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  synchronized private void send(String message) {
    for (PrintStream out : outPutStreams) {
      try {
        out.println(message);
      } catch (Exception e) {
        System.out.println("클라이언트에게 메세지를 발송하던중 오류발생 ");
        // 출력이 안되는 스트림은 다음에 사용하지않기위해 목록에서 제거한다
        outPutStreams.remove(out);
      }
    }
  }
   class ChatAgent implements Runnable {
    Socket socket;

    public ChatAgent(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        Socket socket = this.socket;
        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 출력스트림을 ChatServer에 보관한다
        outPutStreams.add(out);
        while (true) {
          String message = in.readLine();
          if (message.equals("quit"))
            break;

          // 채팅방에 참여한 모든사람들에게 퇴장메세지를 전달한다
          // 메세지를 전문적으로 보내는 일을 하는 객체에 맡긴다
          new Thread(new MessageSender(message)).start();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("채팅서버가 종료되었음");
    }

  }
  class MessageSender implements Runnable {
    String message;

    public MessageSender(String message) {
      this.message = message;
    }

    @Override
    public void run() {
     // 바깥 클래스의 메서드를 호출하여 메세지를 보낸다 
      send(message);
    }
  }

  public static void main(String[] args) {

    ChatServer chatServer = new ChatServer(8888);
    chatServer.service();

  }

}
