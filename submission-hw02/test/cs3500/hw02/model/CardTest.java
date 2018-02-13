package cs3500.hw02.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CardTest {
  Card card1 = new Card(Suit.DIAMONDS, Value.FIVE);
  Card card2 = new Card(Suit.HEARTS, Value.FIVE);
  Card card3 = new Card(Suit.DIAMONDS, Value.JACK);
  Card card4 = new Card(Suit.DIAMONDS, Value.FIVE);
  Card card5 = new Card(Suit.CLUBS, Value.ACE);

  // Test that two cards with the same value but different suits will not be equal
  @Test
  public void testEqualsDifferentSuitSameValue() {
    assertNotEquals(card1, card2);
  }

  // Test that two cards with the same suit but different values will not be equal
  @Test
  public void testEqualsSameSuitDifferentValue() {
    assertNotEquals(card1, card3);
  }

  // Test that two cards with the same value and suit will not be equal
  @Test
  public void testEqualSameSuitSameValue() {
    assertEquals(card1, card4);
  }

  // Test that two cards with different values and suits will not be equal
  @Test
  public void testEqualsDifferentSuitDifferentValue() {
    assertNotEquals(card1, card5);
  }

  // Test the toString method in Card
  @Test
  public void testToString() {
    assertEquals(card4.toString(), "5♦");
  }

  // Test the getColor method in Card
  @Test
  public void testGetColor() {
    assertEquals(card4.getColor(), Color.RED);
  }

  // Test the getSuit method in Card
  @Test
  public void testGetSuit() {
    assertEquals(card4.getSuit(), Suit.DIAMONDS);
  }

  // Test the getValue method in Card
  @Test
  public void testGetValue() {
    assertEquals(card4.getValue(), Value.FIVE);
  }
}