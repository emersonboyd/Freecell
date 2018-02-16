package cs3500.hw02.model;

import java.util.Objects;

/**
 * Represents a playing card.
 */
public class Card {
  private Suit suit;
  private Value value;

  /**
   * Constructs a playing card based on its suit and value.
   *
   * @param suit  the card's suit
   * @param value the card's value
   */
  public Card(Suit suit, Value value) {
    this.suit = suit;
    this.value = value;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Card) {
      Card otherCard = (Card) other;
      return this.suit == otherCard.suit && this.value == otherCard.value;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.suit, this.value);
  }

  public Color getColor() {
    return suit.getColor();
  }

  public Suit getSuit() {
    return suit;
  }

  public Value getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString() + suit.toString();
  }
}
