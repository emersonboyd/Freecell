package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cs3500.hw02.model.Card;
import cs3500.hw02.model.Suit;
import cs3500.hw02.model.Value;

/**
 * An implementation of the FreecellOperation interface for the Freecell game.
 */
public class FreecellModel implements FreecellOperations<Card> {
  protected List<List<Card>> cascadePiles;
  protected List<List<Card>> openPiles;
  protected List<List<Card>> foundationPiles;

  private static final int NUM_SUITS = 4;
  private static final int NUM_VALUES = 13;
  private static final int DECK_SIZE = NUM_SUITS * NUM_VALUES;

  public static final String ERROR_DECK_INVALID =
          "Deck is invalid";
  public static final String ERROR_PILE_NUM =
          "Must have at least 4 cascade piles and at least 1 open pile";

  public static final String ERROR_MOVE_BEFORE_GAME_START =
          "Cannot move cards before game has started";
  public static final String ERROR_MOVE_FROM_FOUNDATION_PILE =
          "Cannot move a card from a foundation pile";
  public static final String ERROR_OPEN_PILE_NUMBER_OUT_OF_BOUNDS =
          "Open pile number is out of bounds";
  public static final String ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS =
          "Cascade pile number is out of bounds";
  public static final String ERROR_CARD_INDEX_OUT_OF_BOUNDS =
          "Card index out of bounds. Must be last card in pile";
  public static final String ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS =
          "Destination pile number is out of bounds";
  public static final String ERROR_MISMATCH_SUIT_IN_THIS_FOUNDATION =
          "The card being moved to a foundation pile does not match suit";
  public static final String ERROR_MISMATCH_VALUE_IN_THIS_FOUNDATION =
          "Card value for foundation pile placement is invalid";
  public static final String ERROR_OPEN_PILE_ALREADY_FILLED =
          "Cannot place a card in an already-filled open pile";
  public static final String ERROR_MISMATCH_CARD_IN_THIS_CASCADE =
          "Moved card must be the opposite color and one less value than the top of the cascade " +
                  "pile";

  /**
   * Constructs a default empty FreecellModel.
   */
  public FreecellModel() {
    // No need to initialize anything because we use the null piles to check state
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>();

    for (int i = 0; i < DECK_SIZE; i++) {
      Suit suit;
      Value value;

      switch (i % NUM_SUITS) {
        case 0:
          suit = Suit.DIAMONDS;
          break;
        case 1:
          suit = Suit.SPADES;
          break;
        case 2:
          suit = Suit.HEARTS;
          break;
        case 3:
          suit = Suit.CLUBS;
          break;
        default:
          suit = null;
          break;
      }

      switch (i % NUM_VALUES) {
        case 0:
          value = Value.ACE;
          break;
        case 1:
          value = Value.TWO;
          break;
        case 2:
          value = Value.THREE;
          break;
        case 3:
          value = Value.FOUR;
          break;
        case 4:
          value = Value.FIVE;
          break;
        case 5:
          value = Value.SIX;
          break;
        case 6:
          value = Value.SEVEN;
          break;
        case 7:
          value = Value.EIGHT;
          break;
        case 8:
          value = Value.NINE;
          break;
        case 9:
          value = Value.TEN;
          break;
        case 10:
          value = Value.JACK;
          break;
        case 11:
          value = Value.QUEEN;
          break;
        case 12:
          value = Value.KING;
          break;
        default:
          value = null;
          break;
      }

      deck.add(new Card(suit, value));
    }

    return deck;
  }

  /**
   * Shuffles the current deck by randomly reorganizing the cards.
   *
   * @return the shuffled deck
   */
  private List<Card> shuffleDeck(List<Card> deck) {
    Collections.shuffle(deck);

    return deck;
  }

  /**
   * Returns whether the deck is valid according to the rules defined for a deck in the
   * FreecellOperations interface.
   *
   * @return true if the deck is valid, false otherwise
   */
  public boolean isDeckValid(List<Card> deck) {
    // check that the deck has 52 cards
    if (deck.size() != DECK_SIZE) {
      return false;
    }

    // check that the deck does not contain duplicates
    Set<Card> deckSet = new HashSet<Card>(deck);
    return deckSet.size() == deck.size();
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
          throws IllegalArgumentException {
    if (!isDeckValid(deck)) {
      throw new IllegalArgumentException(ERROR_DECK_INVALID);
    }

    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException(ERROR_PILE_NUM);
    }

    if (shuffle) {
      shuffleDeck(deck);
    }

    cascadePiles = new ArrayList<List<Card>>(numCascadePiles);
    for (int i = 0; i < numCascadePiles; i++) {
      cascadePiles.add(new ArrayList<Card>());
    }

    openPiles = new ArrayList<List<Card>>(numOpenPiles);
    for (int i = 0; i < numOpenPiles; i++) {
      openPiles.add(new ArrayList<Card>());
    }

    foundationPiles = new ArrayList<List<Card>>(NUM_SUITS);
    for (int i = 0; i < 4; i++) {
      foundationPiles.add(new ArrayList<Card>(NUM_VALUES));
    }

    // fill the cascade piles
    for (int i = 0; i < deck.size(); i++) {
      int cascadePileIndex = i % numCascadePiles;

      cascadePiles.get(cascadePileIndex).add(deck.get(i));
    }
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
        break;
      case CASCADE:
        // check if the given pileNumber is valid
        if (pileNumber < 0 || pileNumber >= cascadePiles.size()) {
          throw new IllegalArgumentException(ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS);
        } else {
          sourcePile = cascadePiles.get(pileNumber);
        }
        break;
      default:
        // do nothing because all source cases should be handled above
        break;
    }

    // check if the given cardIndex is valid
    if (cardIndex + 1 != sourcePile.size()) {
      throw new IllegalArgumentException(ERROR_CARD_INDEX_OUT_OF_BOUNDS);
    }

    Card sourceCard = sourcePile.get(cardIndex);

    // check if the given source location is the same as the destination location
    if (source == destination && pileNumber == destPileNumber) {
      // in this case, we just want to do nothing (because it's a valid move with no operation)
      return;
    }

    List<Card> destPile = null;

    switch (destination) {
      case FOUNDATION:
        // check to see if the destPileNumber is valid
        if (destPileNumber < 0 || destPileNumber >= foundationPiles.size()) {
          throw new IllegalArgumentException(ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
        }

        // check to see if this card's suit lines up
        if (foundationPiles.get(destPileNumber).size() > 0
                && foundationPiles.get(destPileNumber).get(0).getSuit() != sourceCard.getSuit()) {
          throw new IllegalArgumentException(ERROR_MISMATCH_SUIT_IN_THIS_FOUNDATION);
        }

        // check to see if the card's value lines up
        if (sourceCard.getValue().getNumber() != foundationPiles.get(destPileNumber).size() + 1) {
          throw new IllegalArgumentException(ERROR_MISMATCH_VALUE_IN_THIS_FOUNDATION);
        }

        destPile = foundationPiles.get(destPileNumber);
        break;

      case OPEN:
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
          Card cascadePileTop = cascadePiles.get(destPileNumber)
                  .get(cascadePiles.get(destPileNumber).size() - 1);

          // check to see if the suit/value cooperates with the top card in the pile
          if (sourceCard.getColor() == cascadePileTop.getColor()
                  || sourceCard.getValue().getNumber() + 1
                  != cascadePileTop.getValue().getNumber()) {
            throw new IllegalArgumentException(ERROR_MISMATCH_CARD_IN_THIS_CASCADE);
          }
        }

        destPile = cascadePiles.get(destPileNumber);
        break;

      default:
        // do nothing because all destination cases should be handled above
        break;
    }

    // once the transaction has been validated, we can make the move
    sourcePile.remove(cardIndex);
    destPile.add(sourceCard);
  }

  /**
   * Checks whether the game has started or not based on the state of the piles.
   *
   * @return true if the game has started, false otherwise
   */
  private boolean hasGameStarted() {
    return cascadePiles != null && openPiles != null && foundationPiles != null;
  }

  @Override
  public boolean isGameOver() {
    // check that the cascade piles are empty
    for (int i = 0; i < cascadePiles.size(); i++) {
      if (cascadePiles.get(i).size() > 0) {
        return false;
      }
    }

    // check that the open piles are empty
    for (int i = 0; i < openPiles.size(); i++) {
      if (openPiles.get(i).size() > 0) {
        return false;
      }
    }

    // check that all the fondation piles are filled properly
    for (int i = 0; i < foundationPiles.size(); i++) {
      if (!isFoundationPileFull(foundationPiles.get(i))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Checks if all of the cards in the given pile are in ascending order and are all the same suit,
   * and whether there are 13 cards or not.
   *
   * @param foundationPile the individual pile to check against
   * @return true if the pile is complete, false otherwise
   */
  private boolean isFoundationPileFull(List<Card> foundationPile) {
    if (foundationPile.size() != 13) {
      return false;
    }

    Suit originalSuit = foundationPile.get(0).getSuit();

    for (int i = 0; i < foundationPile.size(); i++) {
      Card currentCard = foundationPile.get(i);

      // check that the card's value and suit are correct for its location in the pile
      if (currentCard.getValue().getNumber() - 1 != i
              || currentCard.getSuit() != originalSuit) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String getGameState() {
    if (!hasGameStarted()) {
      return "";
    }

    StringBuilder result = new StringBuilder();

    result.append(getListOfPilesState(foundationPiles, "F"));
    result.append("\n");
    result.append(getListOfPilesState(openPiles, "O"));
    result.append("\n");
    result.append(getListOfPilesState(cascadePiles, "C"));

    return result.toString();
  }

  /**
   * Creates a string based on the state of a list of piles, separating piles by new lines.
   *
   * @param listOfPiles  the list of card piles to create the string of
   * @param abbreviation the string to append at the beginning of each line
   * @return the string describing the state of the list of piles
   */
  private StringBuilder getListOfPilesState(List<List<Card>> listOfPiles, String abbreviation) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < listOfPiles.size(); i++) {
      if (i > 0) {
        result.append("\n");
      }

      result.append(abbreviation)
              .append(i + 1)
              .append(":");

      if (listOfPiles.get(i).size() > 0) {
        result.append(" ")
                .append(getPileState(listOfPiles.get(i)));
      }
    }

    return result;
  }

  /**
   * Creates a string based on the state of a single pile, separating cards by commas.
   *
   * @param pile the pile to create the string of
   * @return the string describing the state of the pile
   */
  private String getPileState(List<Card> pile) {
    return pile.stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "));
  }
}
