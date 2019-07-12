package ch12.d;

public class Patient3 {
  public static final int WOMAN = 1;
  public static final int MAN = 2;
  private String name;
  private int age;

  private int height;
  private int weight;
  private int gender;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setAge(int age) {
    if (age > 0 & age < 150)
      this.age = age;
    else
      throw new RuntimeException("나이가 유효하지않습니다.");
  }

  public int getAge() {
    return age;
  }


  public void setHeight(int height) {
    if (height > 1 && height < 300)
      this.height = height;
    else
      throw new RuntimeException("키가 유효하지않습니다.");

  }

  public int getHeight() {
    return height;

  }

  public void setWeight(int weight) {
    if (weight > 1 && weight < 1000)
      this.weight = weight;
    else
      throw new RuntimeException("무게가 유효하지않습니다.");

  }

  public int getWeight() {
    return weight;
  }

  public void setGender(int gender) {
    if (gender > 0 && gender < 3)
      this.gender = gender;
    else
      throw new RuntimeException("성별이 유효하지않습니다.");
  }

  public int getGender() {
    return gender;
  }

  public String toString() {

    return String.format("name=%s,age=%d,height=%d,weight=%d,gender=%d", this.name, this.age,
        this.height, this.weight, this.gender);

  }
}
