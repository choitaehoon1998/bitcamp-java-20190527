package ch29.g;

import java.beans.PropertyEditorSupport;

public class MyCustomBlackBoxEditor extends PropertyEditorSupport{
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    String[] a= text.split(",");
    BlackBox blackBox = new BlackBox();
    blackBox.setMaker(a[0]);
    blackBox.setModel(a[1]);

    this.setValue(blackBox);
  }
}
