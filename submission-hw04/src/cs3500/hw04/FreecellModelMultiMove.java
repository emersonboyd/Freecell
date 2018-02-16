package cs3500.hw04;

import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;
import cs3500.hw02.model.Card;

/**
 * An implementation of the FreecellOperation interface for the Freecell game that allows for
 * multiple cards to be moved in a cascade pile in one move so long as it is a legal move.
 */
public class FreecellModelMultiMove extends FreecellModel {
  public static final String ERROR_CANNOT_CREATE_VALID_BUILD_FROM_SOURCE =
          "The given card index does not create a valid build pile with alternating colors and " +
                  "descending values.";
  public static final String ERROR_NOT_ENOUGH_EMPTY_PILES =
          "There are not enough empty piles to temporarily hold cards for this move";
  public static final String ERROR_MOVE_MULTIPLE_CARDS_TO_FOUNDATION_PILE =
          "Cannot move multiple cards to a foundation pile.";
  public static final String ERROR_MOVE_MULTIPLE_CARDS_TO_OPEN_PILE =
          "Cannot move multiple cards to an open pile.";
  public static final String ERROR_CANNOT_INSERT_BUILD_TO_DESTINATION_CASCADE =
          "The destination cascade pile cannot hold the build pile.";

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
    if (!hasGameStarted()) {
      throw new IllegalStateException(ERROR_MOVE_BEFORE_GAME_START);
    }

    List<Card> sourcePile = null;

    switch (source) {
      case FOUNDATION:
        // we don't want to move anything out of the foundation pile
        throw new IllegalArgumentException(ERROR_MOVE_FROM_FOUNDATION_PILE);
      case OPEN:
        // check if the given pileNumber is valid
        if (pileNumber < 0 || pileNumber >= openPiles.size()) {
          throw new IllegalArgumentException(ERROR_OPEN_PILE_NUMBER_OUT_OF_BOUNDS);
        } else {
          sourcePile = openPiles.get(pileNumber);
        }

        // check if the given cardIndex is valid
        if (cardIndex < 0 || cardIndex + 1 != sourcePile.size()) {
          throw new IllegalArgumentException(ERROR_CARD_INDEX_OUT_OF_BOUNDS);
        }
        break;
      case CASCADE:
        // check if the given pileNumber is valid
        if (pileNumber < 0 || pileNumber >= cascadePiles.size()) {
          throw new IllegalArgumentException(ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS);
        } else {
          sourcePile = cascadePiles.get(pileNumber);
        }

        // check if the given cardIndex is valid
        if (cardIndex >= sourcePile.size() || cardIndex < 0) {
          throw new IllegalArgumentException(ERROR_CARD_INDEX_OUT_OF_BOUNDS);
        }

        // check if the source pile can be created into a valid build
        if (!isValidBuild(sourcePile.subList(cardIndex, sourcePile.size()))) {
          throw new IllegalArgumentException(ERROR_CANNOT_CREATE_VALID_BUILD_FROM_SOURCE);
        }
        break;
      default:
        // do nothing because all source cases should be handled above
        break;
    }

    List<Card> sourceBuild = sourcePile.subList(cardIndex, sourcePile.size());

    // check if the given source location is the same as the destination location
    if (source == destination && pileNumber == destPileNumber) {
      // in this case, we just want to do nothing (because it's a valid move with no operation)
      return;
    }

    // check if there are enough intermediate piles to make the move
    if (sourceBuild.size() >
            (countEmptyPiles(PileType.OPEN) + 1)
                    * Math.pow(2, countEmptyPiles(PileType.CASCADE))) {
      throw new IllegalArgumentException(ERROR_NOT_ENOUGH_EMPTY_PILES);
    }

    List<Card> destPile = null;

    switch (destination) {
      case FOUNDATION:
        // check to ensure we are only moving one card to the foundation pile
        if (sourceBuild.size() > 1) {
          throw new IllegalArgumentException(ERROR_MOVE_MULTIPLE_CARDS_TO_FOUNDATION_PILE);
        }

        // check to see if the destPileNumber is valid
        if (destPileNumber < 0 || destPileNumber >= foundationPiles.size()) {
          throw new IllegalArgumentException(ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
        }

        Card sourceCard = sourceBuild.get(0);

        // check to see if this card's suit lines up
        if (foundationPiles.get(destPileNumber).size() > 0
                && foundationPiles.get(destPileNumber).get(0).getSuit() != sourceCard.getSuit()) {
          throw new IllegalArgumentException(ERROR_MISMATCH_SUIT_IN_THIS_FOUNDATION);
        }

        // check to see if the card's value lines up
        if (sourceCard.getValue().getNumber()
                != foundationPiles.get(destPileNumber).size() + 1) {
          throw new IllegalArgumentException(ERROR_MISMATCH_VALUE_IN_THIS_FOUNDATION);
        }

        destPile = foundationPiles.get(destPileNumber);
        break;

      case OPEN:
        // check to ensure we are only moving one card to the open pile
        if (sourceBuild.size() > 1) {
          throw new IllegalArgumentException(ERROR_MOVE_MULTIPLE_CARDS_TO_OPEN_PILE);
        }

        // check to see if the destPileNumber is valid
        if (destPileNumber < 0 || destPileNumber >= openPiles.size()) {
          throw new IllegalArgumentException(ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
        }

        // check to see if there's already a card in the open pile
        if (openPiles.get(destPileNumber).size() > 0) {
          throw new IllegalArgumentException(ERROR_OPEN_PILE_ALREADY_FILLED);
        }

        destPile = openPiles.get(destPileNumber);
        break;

      case CASCADE:
        // check to see if the destPileNumber is valid
        if (destPileNumber < 0 || destPileNumber >= cascadePiles.size()) {
          throw new IllegalArgumentException(ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
        }

        // check to see if the card can be placed above the previous card
        if (cascadePiles.get(destPileNumber).size() > 0) {
          Card sourceBuildBottom = sourceBuild.get(0);
          Card cascadePileTop = cascadePiles.get(destPileNumber)
                  .get(cascadePiles.get(destPileNumber).size() - 1);

          // check to see if the suit/value cooperates with the top card in the pile
          if (sourceBuildBottom.getColor() == cascadePileTop.getColor()
                  || sourceBuildBottom.getValue().getNumber() + 1
                  != cascadePileTop.getValue().getNumber()) {
            throw new IllegalArgumentException(ERROR_CANNOT_INSERT_BUILD_TO_DESTINATION_CASCADE);
          }
        }

        destPile = cascadePiles.get(destPileNumber);
        break;

      default:
        // do nothing because all destination cases should be handled above
        break;
    }

    // once the transaction has been validated, we can make the move
    destPile.addAll(sourceBuild);
    sourcePile.removeAll(sourceBuild);
  }

  /**
   * Checks whether the game has started or not based on the state of the piles.
   *
   * @return true if the game has started, false otherwise
   */
  private boolean hasGameStarted() {
    return cascadePiles != null && openPiles != null && foundationPiles != null;
  }

  /**
   * Says whether the given list is a valid build. A valid build hould be arranged in alternating
   * colors and consecutive, descending values in the list.
   *
   * @param buildList the list to check if it's a build
   * @return return true if the given list is a valid build, false otherwise
   */
  private boolean isValidBuild(List<Card> buildList) {
    if (buildList.size() == 1) {
      return true;
    }

    if (buildList.get(0).getValue().getNumber() - 1 == buildList.get(1).getValue().getNumber()
            && buildList.get(0).getColor() != buildList.get(1).getColor()) {
      return isValidBuild(buildList.subList(1, buildList.size()));
    }

    return false;
  }

  /**
   * Counts the number of empty piles of the given PileType.
   *
   * @param pileType the type of pile to count the number of empties
   * @return the number of empty piles of the given PileType
   */
  private int countEmptyPiles(PileType pileType) {
    List<List<Card>> piles = null;

    switch (pileType) {
      case FOUNDATION:
        piles = foundationPiles;
        break;
      case OPEN:
        piles = openPiles;
        break;
      case CASCADE:
        piles = cascadePiles;
        break;
      default:
        break;
    }

    int numAvailable = 0;

    for (List<Card> pile : piles) {
      if (pile.size() == 0) {
        numAvailable++;
      }
    }

    return numAvailable;
  }
}
