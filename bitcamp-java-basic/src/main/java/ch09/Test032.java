package ch09;

public class Test032 {

  public static void main(String[] args) {
    // 계산하기 => 2*3-2+7=?
    //          6/2+4?
    //calculator 클래스 에는 result 변수가 한개만 있기떄문에 
    //두개의 계산식을 동시에 수행할수없다
    // 다음과같이 한개의식을 모두계산한 다음에 
    //두번째 식을 계산해야한다 
    //이것이 클래스필듸 한계이다 
    //해결책: 개별적으로 관리되어야하는값은 인스턴스 변수에 저장ㅇ한다 
    Calculator.plus(2);
    Calculator.multiple(3);
    Calculator.minus(2);
    Calculator.plus(7);

    System.out.printf("결과 = %d\n ",Calculator.result);
    
    Calculator.result=0;
    Calculator.plus(6);
    Calculator.divide(2);
    Calculator.plus(4);
  }

}
