package cs3500.hw02.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueTest {

  // Test that the four produces the right numerical value
  @Test
  public void testGetNumber1() {
    assertEquals(Value.FOUR.getNumber(), 4);
  }

  // Test that the jack produces the right numerical value
  @Test
  public void testGetNumber2() {
    assertEquals(Value.JACK.getNumber(), 11);
  }

  // Test that the ace produces the right numerical value
  @Test
  public void testGetNumber3() {
    assertEquals(Value.ACE.getNumber(), 1);
  }

  // Test that the five produces the right toString
  @Test
  public void testToString1() {
    assertEquals(Value.FIVE.toString(), "5");
  }

  // Test that the queen produces the right toString
  @Test
  public void testToString2() {
    assertEquals(Value.QUEEN.toString(), "Q");
  }

  // Test that the ace produces the right toString
  @Test
  public void testToString3() {
    assertEquals(Value.ACE.toString(), "A");
  }
}