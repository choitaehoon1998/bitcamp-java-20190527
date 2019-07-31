package design_pattern.observer2.after.version1;
//옵저버에게 한 문자를 읽을때마다 알려주기 위해 메서드를 호출할떄
//그 호출할 메서드의 규칙을 정해준다 . 
public interface CharacterListener {
void readed (int ch);
void displayResult();
}
