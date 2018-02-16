package cs3500.hw03;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import cs3500.hw02.model.Card;

/**
 * An implementation of the IFreecellController interface for the Freecell game using class Card.
 * Interacts with the FreecellModel interface to actually control the gameplay of Freecell.
 */
public class FreecellController implements IFreecellController<Card> {
  public static final String ERROR_NULL_READABLE_OR_APPENDABLE =
          "Cannot construct FreecellController with null Readable or null Appendable";
  public static final String ERROR_NULL_DECK_OR_MODEL =
          "The deck and model cannot be null";
  public static final String ERROR_INVALID_PILETYPE_CHARACTER =
          "Cannot parse given PileType character input";

  public static final String WARNING_INVALID_START_GAME_PARAMS =
          "Could not start game.";
  public static final String WARNING_INVALID_FROM_PILE_TYPE_AND_NUMBER_COMMAND =
          "\nReceived an invalid command for the pile type/number to move from";
  public static final String WARNING_INVALID_FROM_CARD_INDEX_COMMAND =
          "\nReceived an invalid number for the pile card index to move from";
  public static final String WARNING_INVALID_TO_PILE_TYPE_AND_NUMBER_COMMAND =
          "\nReceived an invalid command for the pile type/number to move to";
  public static final String WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL =
          "\nInvalid move. Try again. ";

  public static final String MESSAGE_GAME_QUIT_PREMATURELY =
          "\nGame quit prematurely.";
  public static final String MESSAGE_GAME_OVER =
          "\nGame over.";

  final Readable in;
  final Appendable out;

  /**
   * Constructs a FreecellController using the given Readable and Appendable objects.
   *
   * @param rd used to take user input
   * @param ap used to transmit output
   * @throws IllegalArgumentException if and only if the readable or appendable objects are null
   */
  public FreecellController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException(ERROR_NULL_READABLE_OR_APPENDABLE);
    }

    this.in = rd;
    this.out = ap;
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                       int numOpens, boolean shuffle) throws IllegalArgumentException, IOException {
    if (deck == null || model == null) {
      throw new IllegalArgumentException(ERROR_NULL_DECK_OR_MODEL);
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      this.out.append(WARNING_INVALID_START_GAME_PARAMS);
      return;
    }

    this.out.append(model.getGameState());

    Scanner scanner = new Scanner(this.in);

    // initialize all of the move parameters to null
    PileType pileFrom = null;
    Integer pileNumberFrom = null;
    Integer cardIndexFrom = null;
    PileType pileTo = null;
    Integer pileNumberTo = null;

    while (!model.isGameOver()) {
      String nextCommand = scanner.next();

      // check if the user wants to quit the game
      if (nextCommand.equalsIgnoreCase("q")) {
        this.out.append(MESSAGE_GAME_QUIT_PREMATURELY);
        return;
      }

      // determine which command parameter the user needs to enter, and make a move if
      if (pileFrom == null && pileNumberFrom == null) {
        if (isValidPile(nextCommand)) {
          pileFrom = parsePileType(nextCommand.charAt(0));
          pileNumberFrom = Integer.parseInt(nextCommand.substring(1)) - 1;
        } else {
          this.out.append(WARNING_INVALID_FROM_PILE_TYPE_AND_NUMBER_COMMAND);
        }
      } else if (cardIndexFrom == null) {
        if (isValidInteger(nextCommand)) {
          cardIndexFrom = Integer.parseInt(nextCommand) - 1;
        } else {
          this.out.append(WARNING_INVALID_FROM_CARD_INDEX_COMMAND);
        }
      } else if (pileTo == null && pileNumberTo == null) {
        if (isValidPile(nextCommand)) {
          pileTo = parsePileType(nextCommand.charAt(0));
          pileNumberTo = Integer.parseInt(nextCommand.substring(1)) - 1;
        } else {
          this.out.append(WARNING_INVALID_TO_PILE_TYPE_AND_NUMBER_COMMAND);
        }
      }

      // make the move within the model, if necessary
      if (pileFrom != null && pileNumberFrom != null && cardIndexFrom != null && pileTo != null
              && pileNumberTo != null) {
        // try to make the move based on the parameters given
        try {
          model.move(pileFrom, pileNumberFrom, cardIndexFrom, pileTo, pileNumberTo);

          // display the current game state to the user after making the move
          this.out.append("\n").append(model.getGameState());

          // reset the game parameters to null so that we can parse future user input
          pileFrom = null;
          pileNumberFrom = null;
          cardIndexFrom = null;
          pileTo = null;
          pileNumberTo = null;
        } catch (IllegalArgumentException e) {
          // let the user know that the model forwarded an error
          this.out.append(WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL)
                  .append(e.getMessage());

          // reset the game parameters to null so that we can parse future user input
          pileFrom = null;
          pileNumberFrom = null;
          cardIndexFrom = null;
          pileTo = null;
          pileNumberTo = null;
        }
      }
    }

    this.out.append(MESSAGE_GAME_OVER);
  }

  /**
   * Says whether the given string encodes to a valid pile type/number.
   *
   * @param pileString the string to check the encoding for
   * @return true if the encoding translates to a valid pile type/number, false otherwise
   */
  public static boolean isValidPile(String pileString) {
    if (pileString.length() < 2) {
      return false;
    }

    // check if the first character is a valid pile type
    char firstChar = pileString.charAt(0);
    if (firstChar != 'C' && firstChar != 'F' && firstChar != 'O') {
      return false;
    }

    // check if the remaining text is a valid pile number
    return isValidInteger(pileString.substring(1));
  }

  /**
   * Says whether the given string is an integer.
   *
   * @param numberString the string to check if it's an integer
   * @return true if the given string is an integer, false otherwise
   */
  public static boolean isValidInteger(String numberString) {
    return Pattern.matches("[-]*[0-9]+", numberString);
  }

  /**
   * Determines the correct PileType from the given input character.
   *
   * @param pileTypeString the character to parse to determine PileType
   * @return the PileType associated with the given character
   * @throws IllegalArgumentException if the pileTypeString is not a valid character to parse
   */
  public static PileType parsePileType(char pileTypeString) throws IllegalArgumentException {
    switch (pileTypeString) {
      case 'C':
        return PileType.CASCADE;
      case 'F':
        return PileType.FOUNDATION;
      case 'O':
        return PileType.OPEN;
      default:
        throw new IllegalArgumentException(ERROR_INVALID_PILETYPE_CHARACTER);
    }
  }
}
