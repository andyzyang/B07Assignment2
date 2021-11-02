package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import exceptions.InvalidArgumentException;
import executables.History;
import input.Input;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.Output;

public class HistoryTest {

  private Output out;
  private Output err;

  @Before
  public void setUp() {
    out = new MockOutput();
    err = new MockOutput();
  }

  @Test
  public void testNoArgsAndNoHistory() {
    List<Input> inputHistory = Arrays.asList();
    List<String> args = Arrays.asList();
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testNoArgsAnd1History() {
    List<Input> inputHistory = Arrays.asList(new MockInput("input1"));
    List<String> args = Arrays.asList();
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("1. input1" + System.lineSeparator(), out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testNoArgsAnd3History() {
    List<Input> inputHistory = Arrays
        .asList(new MockInput("input1"), new MockInput("input2"),
            new MockInput("input3"));
    List<String> args = Arrays.asList();
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("1. input1" + System.lineSeparator() + "2. input2" + System
            .lineSeparator() + "3. input3" + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testLast0AndNoHistory() {
    List<Input> inputHistory = Arrays.asList();
    List<String> args = Arrays.asList("0");
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testLast0And1History() {
    List<Input> inputHistory = Arrays.asList(new MockInput("input1"));
    List<String> args = Arrays.asList("0");
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testLast0And3History() {
    List<Input> inputHistory = Arrays
        .asList(new MockInput("input1"), new MockInput("input2"),
            new MockInput("input3"));
    List<String> args = Arrays.asList("0");
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("", out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testOutOfBoundsHistory() {
    List<Input> inputHistory = Arrays
        .asList(new MockInput("input1"), new MockInput("input2"),
            new MockInput("input3"));
    List<String> args = Arrays.asList("10");
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("1. input1" + System.lineSeparator() + "2. input2" + System
            .lineSeparator() + "3. input3" + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test
  public void testOutOfBoundsHistoryEdgeCase() {
    List<Input> inputHistory = Arrays
        .asList(new MockInput("input1"), new MockInput("input2"),
            new MockInput("input3"));
    List<String> args = Arrays.asList("4");
    assertFalse(new History(inputHistory).execute(args, out, err));
    assertEquals("1. input1" + System.lineSeparator() + "2. input2" + System
            .lineSeparator() + "3. input3" + System.lineSeparator(),
        out.toString());
    assertEquals("", err.toString());
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNegativeNumber() {
    List<Input> inputHistory = Arrays
        .asList(new MockInput("input1"), new MockInput("input2"),
            new MockInput("input3"));
    List<String> args = Arrays.asList("-1");
    new History(inputHistory).execute(args, out, err);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNonIntegerNumber() {
    List<Input> inputHistory = Arrays
        .asList(new MockInput("input1"), new MockInput("input2"),
            new MockInput("input3"));
    List<String> args = Arrays.asList("0.5");
    new History(inputHistory).execute(args, out, err);
  }

  @Test(expected = InvalidArgumentException.class)
  public void testNonNumber() {
    List<Input> inputHistory = Arrays
        .asList(new MockInput("input1"), new MockInput("input2"),
            new MockInput("input3"));
    List<String> args = Arrays.asList("a");
    new History(inputHistory).execute(args, out, err);
  }
}