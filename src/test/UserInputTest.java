// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: yangzon7
// UT Student #: 1004453423
// Author: Zongye Yang
//
// Student2:
// UTORID user_name: langyu1
// UT Student #: 1004026895
// Author: Yu Qiang Lang
//
// Student3:
// UTORID user_name: makgabri
// UT Student #: 1003591400
// Author: Gabrian Mak
//
// Student4:
// UTORID user_name: taojia5
// UT Student #: 1004512486
// Author: Jia Qi Tao
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package test;

import static org.junit.Assert.assertEquals;

import exceptions.ParsingException;
import input.UserInput;
import java.util.Arrays;
import org.junit.Test;

public class UserInputTest {

  @Test(expected = ParsingException.class)
  public void testQuoteInQuotes() {
    new UserInput("\"asd\"sad\"");
  }

  @Test(expected = ParsingException.class)
  public void testUnclosedQuotes() {
    new UserInput("cmd \"asd");
  }

  @Test
  public void testCommandCommandOnlyNoSpaces() {
    assertEquals("cmd", new UserInput("cmd").getCommand());
  }

  @Test
  public void testCommandEmptyString() {
    assertEquals("", new UserInput("").getCommand());
  }

  @Test
  public void testCommandSpacesOnly() {
    assertEquals("", new UserInput("   ").getCommand());
  }

  @Test
  public void testCommandCommandOnlySpacesInFront() {
    assertEquals("cmd", new UserInput("   cmd").getCommand());
  }

  @Test
  public void testCommandCommandOnlySpacesAtEnd() {
    assertEquals("cmd", new UserInput("cmd   ").getCommand());
  }

  @Test
  public void testCommandCommandOnlySpacesEverywhere() {
    assertEquals("cmd", new UserInput("   cmd   ").getCommand());
  }

  @Test
  public void testCommandCommandAnd3Args() {
    assertEquals("cmd", new UserInput("cmd arg1 arg2 arg3").getCommand());
  }

  @Test
  public void testCommandCommandAnd3ArgsSpacesInFront() {
    assertEquals("cmd", new UserInput("   cmd arg1 arg2 arg3").getCommand());
  }

  @Test
  public void testCommandCommandAnd3ArgsSpacesAtEnd() {
    assertEquals("cmd", new UserInput("cmd arg1 arg2 arg3   ").getCommand());
  }

  @Test
  public void testCommandCommandAnd3ArgsSpacesBetween() {
    assertEquals("cmd", new UserInput("cmd   arg1  arg2   arg3").getCommand());
  }

  @Test
  public void testCommandCommandAnd3ArgsSpacesEverywhere() {
    assertEquals("cmd",
        new UserInput("  cmd  arg1    arg2    arg3    ").getCommand());
  }

  @Test
  public void testArgsCommandOnlyNoSpaces() {
    assertEquals(Arrays.asList(), new UserInput("cmd").getArgs());
  }

  @Test
  public void testArgsEmptyString() {
    assertEquals(Arrays.asList(), new UserInput("").getArgs());
  }

  @Test
  public void testArgsSpacesOnly() {
    assertEquals(Arrays.asList(), new UserInput("   ").getArgs());
  }

  @Test
  public void testArgsCommandOnlySpacesInFront() {
    assertEquals(Arrays.asList(), new UserInput("   cmd").getArgs());
  }

  @Test
  public void testArgsCommandOnlySpacesAtEnd() {
    assertEquals(Arrays.asList(), new UserInput("cmd   ").getArgs());
  }

  @Test
  public void testArgsCommandOnlySpacesEverywhere() {
    assertEquals(Arrays.asList(), new UserInput("   cmd   ").getArgs());
  }

  @Test
  public void testArgsCommandAnd3Args() {
    assertEquals(Arrays.asList("arg1", "arg2", "arg3"),
        new UserInput("cmd arg1 arg2 arg3").getArgs());
  }

  @Test
  public void testArgsCommandAnd3ArgsSpacesInFront() {
    assertEquals(Arrays.asList("arg1", "arg2", "arg3"),
        new UserInput("   cmd arg1 arg2 arg3").getArgs());
  }

  @Test
  public void testArgsCommandAnd3ArgsSpacesAtEnd() {
    assertEquals(Arrays.asList("arg1", "arg2", "arg3"),
        new UserInput("cmd arg1 arg2 arg3   ").getArgs());
  }

  @Test
  public void testArgsCommandAnd3ArgsSpacesBetween() {
    assertEquals(Arrays.asList("arg1", "arg2", "arg3"),
        new UserInput("cmd   arg1  arg2   arg3").getArgs());
  }

  @Test
  public void testArgsCommandAnd3ArgsSpacesEverywhere() {
    assertEquals(Arrays.asList("arg1", "arg2", "arg3"),
        new UserInput("  cmd  arg1    arg2    arg3    ").getArgs());
  }

  @Test
  public void testArgsQuotationMarks() {
    assertEquals(Arrays.asList("arg1", "arg2", "arg3"),
        new UserInput("  cmd  \"arg1\"    arg2    arg3    ").getArgs());
  }

  @Test
  public void testArgsQuotationMarksWithSpaces() {
    assertEquals(Arrays.asList("arg1  arg", "arg2", "arg3"),
        new UserInput("  cmd  \"arg1  arg\"    arg2    arg3    ").getArgs());
  }

  @Test
  public void testArgsMultipleArgsQuotationMarksWithSpaces() {
    assertEquals(Arrays.asList("arg1 arg", "arg2", "arg3"),
        new UserInput("  cmd  \"arg1 arg\"    \"arg2\"    arg3    ").getArgs());
  }

  @Test
  public void testRedirectionNone() {
    assertEquals(Arrays.asList(),
        new UserInput("  cmd  \"arg1 arg\"    \"arg2\"    arg3    ")
            .getRedirection());
  }

  @Test
  public void testRedirectionRedirectionSymbolInString() {
    assertEquals(Arrays.asList(),
        new UserInput("  cmd  \"arg1 > arg\"    \"arg2\"    arg3    ")
            .getRedirection());
  }

  @Test
  public void testRedirectionMinimum() {
    assertEquals(Arrays.asList(">"),
        new UserInput("  cmd  \"arg1 arg\"    \"arg2\"    arg3    >")
            .getRedirection());
  }

  @Test
  public void testRedirectionMinimumWithSpacesAtEnd() {
    assertEquals(Arrays.asList(">"),
        new UserInput("  cmd  \"arg1 arg\"    \"arg2\"    arg3    >  ")
            .getRedirection());
  }

  @Test
  public void testRedirection2RedirectionArgs() {
    assertEquals(Arrays.asList(">", "aa", "bbb"),
        new UserInput("  cmd  \"arg1 arg\"    \"arg2\"    arg3    > aa bbb")
            .getRedirection());
  }

  @Test
  public void testRedirectionManyRedirectionArgs() {
    assertEquals(Arrays.asList(">", "aa", "bbb", "cccc"), new UserInput(
        "  cmd  \"arg1 arg\"    \"arg2\"    arg3    > aa bbb cccc")
        .getRedirection());
  }

}