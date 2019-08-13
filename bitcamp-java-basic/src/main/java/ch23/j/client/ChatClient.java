package ch23.j.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatClient extends Frame implements ActionListener {
  private static final long serialVersionUID = 1L;

  TextField addressTF = new TextField(20);
  TextField portTF = new TextField(4);
  Button connectBtn = new Button("CONNECTION");
  TextArea chattingPane = new TextArea();
  TextField messageTF = new TextField();
  Button sendBtn = new Button("SEND");
  
  Socket socket;
  BufferedReader in;
  PrintStream out;
  public ChatClient(String title) {

    super(title);
    this.setSize(600, 400);

    // 윈도우 관련 이벤트를 처리할 담당자를 설정한다
    // => 옵저버 패턴
    // => 윈도우 이벤트가 발생했을때 보고를 받을 객체를 등록한다 .
    this.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
       try {in.close();} catch(Exception ex) {}
       try {out.close();} catch(Exception ex) {}
       try {socket.close();} catch(Exception ex) {}
       
        System.exit(0);
      }
    });

    Panel topPane = new Panel();
    topPane.add(addressTF);
    topPane.add(portTF);
    topPane.add(connectBtn);
    this.add(topPane, BorderLayout.NORTH);

    this.add(chattingPane, BorderLayout.CENTER);

    Panel bottomPanel = new Panel();
    bottomPanel.setLayout(new BorderLayout());
    bottomPanel.add(messageTF, BorderLayout.CENTER);
    bottomPanel.add(sendBtn, BorderLayout.EAST);
    this.add(bottomPanel, BorderLayout.SOUTH);


    // 연결 버튼에 대해 옵저버 등록하기
    connectBtn.addActionListener(this);

    // 보내기 버튼에 대해 옵저버 등록하기
    sendBtn.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == sendBtn) {
     //서버에전송 
      out.println(messageTF.getText());
      //기존에 입력 한 메세지는 지운다 
      messageTF.setText("");
    }
    if (e.getSource() == connectBtn) {
      connectChatServer();
    }
  }

  private void connectChatServer() {
    try {
      socket = new Socket(addressTF.getText(), Integer.parseInt(portTF.getText()));
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintStream(socket.getOutputStream());
      chattingPane.append("서버와 연결됨 \n");
      
      //서버와 연결되면 메시지 수신을 별도의 담당자에게 맡긴다
      new Thread(new MessageReciever()).start();
      
      //메시지 전송은 버튼을 누를때마다 메인 쓰레드가 처리할 것이다 
    } catch (Exception e) {
      chattingPane.append("서버 연결 오류\n");
    }
  }
 class MessageReciever implements Runnable {
   @Override
   public void run() {
     try {
     while(true) {
       String message = in.readLine();
       chattingPane.append(message+"\n");
     }
   }catch(Exception e) {
     chattingPane.append("메세지수신중 오류발생 \n");
   }
   }
 }
  public static void main(String[] args) {
    System.out.println("Hello!");
    ChatClient app = new ChatClient("비트채팅");
    app.setVisible(true);
  }
}



// java 윈도우 프로그래밍
// 1) AWT
// 2) Swing
// 3) Eclipse SWT
// 4)
