// 기능 확장하기 - Score 클래스를 상속 받아 기능 추가하기
package ch13.b;

public class Test02 {
  public static void main(String[] args) {

    // Score2를 이용하여 수퍼 클래스의 기능을 호출할 수 있다.
    //
    Score2 s = new Score2();
    s.setName("홍길동"); // Score의 메서드
    s.setKor(100); // Score의 메서드
    s.setEng(100); // Score의 메서드
    s.setMath(100); // Score의 메서드
    s.setMusic(100); // Score2의 메서드
    s.setArt(100); // Score2의 메서드
    System.out.printf("%s: %d(%.1f)\n", s.getName(), s.getSum(), s.getAver());

    Score2 s2 = new Score2();
    s2.setName("임꺽정");
    s2.setMusic(100); // Score2 의 compute2 호출=> 5과목의 합계와 평균계산
    s2.setArt(100);// Score2 의 compute2 호출> 5과목의 합계와 평균계산
    s2.setEng(100);//Score 의 compute 호출=>3과목의 합계와 평균계산
    s2.setKor(100);//Score 의 compute 호출=>3과목의 합계와 평균계산
    s2.setMath(100);//Score 의 compute 호출=>3과목의 합계와 평균계산
    System.out.printf("%s: %d(%.1f)\n", s2.getName(), s2.getSum(), s2.getAver());
    //국영수 점수를 설정할떄는 score의 compute()를 호출한다
    //음악 미술 점수를 설정할때는 score2의 compute2()를 호출한다
    //따라서 음악 미술 점수를 먼저설정한다음에 국영수점수를설정하면
    // 합계와 평균이 잘못계산된다
    //해결책1: 국영수 점수를 설정하는 setter에서 compute() 대신에 compute 2를 호출하도록 변경
    //2: compute)가 음악과 미술점수를 함께 계산하도록 변경 !.
    //=> 기존에 작성한 코드를 변경하는것은 유지보수에 바람직하지않기때문에
    //   기존 클래스인 score를 손대지않는 조건으로
    //   score 클래스를 변경해야한다. 
    //   이를 위한 목적으로 탄생한 문법이 바로 '다형성 기법중 오버라이딩' 이다
    //   score 3 클래스를 확인하라 . 
  
  }
}
