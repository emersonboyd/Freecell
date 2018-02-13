package cs3500.hw02.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SuitTest {

  // Test that the diamonds produces the right color
  @Test
  public void testGetColor1() {
    assertEquals(Suit.DIAMONDS.getColor(), Color.RED);
  }

  // Test that the spades produces the right color
  @Test
  public void testGetColor2() {
    assertEquals(Suit.SPADES.getColor(), Color.BLACK);
  }

  // Test that the hearts produces the right toString
  @Test
  public void testToString1() {
    assertEquals(Suit.HEARTS.toString(), "♥");
  }

  // Test that the clubs produces the right toString
  @Test
  public void testToString2() {
    assertEquals(Suit.CLUBS.toString(), "♣");
  }
}