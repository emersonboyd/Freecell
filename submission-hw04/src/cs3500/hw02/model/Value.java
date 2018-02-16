package cs3500.hw02.model;

/**
 * Represents a value that a playing card can take the form of.
 */
public enum Value {
  ACE(1, "A"),
  TWO(2, "2"),
  THREE(3, "3"),
  FOUR(4, "4"),
  FIVE(5, "5"),
  SIX(6, "6"),
  SEVEN(7, "7"),
  EIGHT(8, "8"),
  NINE(9, "9"),
  TEN(10, "10"),
  JACK(11, "J"),
  QUEEN(12, "Q"),
  KING(13, "K");

  private final int number;
  private final String displayString;

  /**
   * Constructs a value based on its corresponding numerical value.
   *
   * @param number this value's corresponding numerical value
   * @throws IllegalArgumentException if {@code number} is not in a reasonable range
   */
  Value(int number, String displayString) {
    if (number < 1 || number > 13) {
      throw new IllegalArgumentException("Invalid numerical value for this card");
    }

    this.number = number;
    this.displayString = displayString;
  }

  /**
   * A getter for this value's corresponding numerical value.
   *
   * @return this value's corresponding numerical value.
   */
  public int getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return displayString;
  }
}
