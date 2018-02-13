package cs3500.hw02.model;

/**
 * Represents a suit that a playing card can take the form of.
 */
public enum Suit {
  DIAMONDS(Color.RED, "♦"),
  SPADES(Color.BLACK, "♠"),
  HEARTS(Color.RED, "♥"),
  CLUBS(Color.BLACK, "♣");

  private final Color color;
  private final String displayValue;

  /**
   * Constructs a playing card suit in terms of its color.
   *
   * @param color this suit's color
   */
  Suit(Color color, String displayValue) {
    if (color != Color.RED && color != Color.BLACK) {
      throw new IllegalArgumentException("Invalid suit color");
    }

    if (!displayValue.equals("♦") && !displayValue.equals("♠")
            && !displayValue.equals("♥") && !displayValue.equals("♣")) {
      throw new IllegalArgumentException("Invalid suit display value");
    }

    this.color = color;
    this.displayValue = displayValue;
  }

  /**
   * A getter for this suit's color.
   *
   * @return this suit's color
   */
  public Color getColor() {
    return color;
  }

  @Override
  public String toString() {
    return displayValue;
  }
}