package algorithm.data_structure.linkedlist2.step3;

public class LinkedListTest {
  public static void main(String[] args) {
    
  LinkedList <String>list = new LinkedList<>();
  list.add("aaa");
  list.add("bbb");
  list.add("ccc");
  list.add("ddd");
  list.add("eee");
  list.add("fff");
  list.add("ggg");
  
  
  String [] arr  = list.toArray(new String[] {});
 // list.clear();
//  list.set(2, "xxx");//ccc->xxx
//  
//  list.remove(3);//ddd삭제
//  list.remove(0);//aaa삭제
//  list.remove(4);//ggg삭제
  
//  list.remove(0);//bbb삭제
//  list.remove(0);//xxx삭제
//  list.remove(0);//eee삭제
//  list.remove(0);//fff삭제
//  
// 
  for(String k : arr) {
    System.out.println(k);
  }
  }
}
