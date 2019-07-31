package design_pattern.observer2.after.version4;

public class ListCommentListener implements CharacterListener{
int lines = 0;
boolean isStartComment = false;
int countSlash = 0;
@Override
public void readed(int ch) {
  if (!isStartComment) {
    if (ch == '/') {
      if (countSlash == 0) {
        countSlash++;
      } else {
        lines++;
        isStartComment = true;
      }
    } else {
      countSlash = 0;
    }
  } else if (ch == '\n') {
    isStartComment = false;
  }
  
}
@Override
public void displayResult() {
  System.out.printf("총 한줄 주석수 : %d \n",lines);
  
}
}
