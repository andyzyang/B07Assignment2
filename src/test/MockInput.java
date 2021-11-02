package test;

import input.Input;

public class MockInput implements Input {

  private String string;

  public MockInput(String string) {
    this.string = string;
  }

  @Override
  public String toString() {
    return string;
  }
}
