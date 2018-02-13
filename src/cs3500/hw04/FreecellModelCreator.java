package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.model.Card;

/**
 * Represents a factory class to return various implementations of the FreecellOperations<Card>
 * interface.
 */
public class FreecellModelCreator {
  public static final String ERROR_ILLEGAL_GAMETYPE = "Inputted game type is not a valid GameType";

  /**
   * Produces a specific implementation of FreecellOperations<Card> based on what the user requests.
   *
   * @param type the type of FreecellOperations<Card> to create
   * @return the FreecellOperations<Card> specified by the GameType
   * @throws IllegalArgumentException if an invalid GameType is passed to the method
   */
  public static FreecellOperations<Card> create(GameType type) throws IllegalArgumentException {
    if (type == GameType.SINGLEMOVE) {
      return new FreecellModel();
    }

    if (type == GameType.MULTIMOVE) {
      return new FreecellModelMultiMove();
    }

    throw  new IllegalArgumentException(ERROR_ILLEGAL_GAMETYPE);
  }

  public enum GameType {
    SINGLEMOVE,
    MULTIMOVE
  }
}
