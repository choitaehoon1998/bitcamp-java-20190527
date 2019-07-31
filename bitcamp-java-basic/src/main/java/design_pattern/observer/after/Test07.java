package design_pattern.observer.after;

public class Test07 {

  public static void main(String[] args) {
    CarObserver o1 = new SafeBeltCarObserver();
    CarObserver o2 =new EngineOilCarObserver();
    CarObserver o3=new BreakOilCarObserver();
    CarObserver o4=new LightOffCarObserver();
    CarObserver o5=new SunRoofCloseCarObserver();
    Car car = new Car();
    //observer 패턴은 리스너를 쉽게 추가하고 제거할수있다. 
    // 언제든 특정상태에 대해 관심이없다면 제거하면된다 . 
    car.addObserver(o1);
    car.addObserver(o2);
    car.addObserver(o3);
    car.addObserver(o4);
    car.addObserver(o5);

    car.start();
    car.run();
    car.stop();
      System.out.println("--------------------------------------------");
      System.out.println("[사용하지않을 옵저버를 제거한후 ] ");
    car.removeObserver(o1);
    car.removeObserver(o3);
    car.removeObserver(o5);

    car.start();
    car.run();
    car.stop();

    
  }

}
