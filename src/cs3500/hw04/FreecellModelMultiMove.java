package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

/**
 * An implementation of the FreecellOperation interface for the Freecell game that allows for
 * multiple cards to be moved in a cascade pile in one move so long as it is a legal move.
 */
public class FreecellModelMultiMove extends FreecellModel {

  /**
   * Constructs a default empty FreecellModelMultiMove.
   */
  public FreecellModelMultiMove() {
    super();
  }

  @Override
  public void move(PileType source,
                   int pileNumber,
                   int cardIndex,
                   PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
  }
}
