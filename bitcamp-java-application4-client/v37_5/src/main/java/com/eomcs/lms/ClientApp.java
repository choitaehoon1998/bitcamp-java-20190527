// client v37_5:서버 종료를 처리하기위해 serverstop 명령을 한번더 보내기 
package com.eomcs.lms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ClientApp {

  public ClientApp() {}

  Scanner keyboard;
  String host ;
  int port;
  private void service() {
    // Command 객체가 사용할 데이터 처리 객체를 준비한다.

    keyboard = new Scanner(System.in);
    System.out.print("서버는 ?");
    host = keyboard.nextLine();

    System.out.print("포트?");
    port = Integer.parseInt(keyboard.nextLine());

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();




    while (true) {

      String command = prompt();
      if (command.length() == 0) {
        continue;
      }
      commandStack.push(command);
      commandQueue.offer(command);


      if (command.equals("history")) {
        printCommandHistory(commandStack);

      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue);

      }  else {
        request(command);
        //서버를 멈출때는 서버가 상태값을 처리할수있도록 한번더 요청을 보낸다 
        if(command.equals("serverstop")) 
          request(command);
        
        if((command.equals("quit") || command.equals("serverstop")) )
          break;
        // 서버에 quit 또는 serverstop명령어를 보내고 종료한다

      }
      System.out.println();
    } // while
  }
  private void request(String command){
    try (Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

      send(command, out);
      receive(in,out);

    } catch (Exception e) {
      System.out.println("애플리케이션 서버와의 통신 오류");
      e.printStackTrace();
    }

  }


  private void send(String command, PrintStream out) throws Exception {
    out.println(command);
    out.flush();
  }

  private void receive(BufferedReader in,PrintStream out) throws Exception {
    while (true) {
      String response = in.readLine();
      if (response.equals("!end!")) { 
        break;
      }else if(response.equals("!{}!")) {
        send(keyboard.nextLine(),out);
      }else {
        System.out.println(response);
      }
    }
  }


  private void printCommandHistory(Iterable<String> list) {
    Iterator<String> iterator = list.iterator();
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if (++count % 5 == 0) {
        System.out.print(":");
        if (keyboard.nextLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }

  private String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine();
  }


  public static void main(String[] args) {
    ClientApp app = new ClientApp();
    app.service();
  }
}


