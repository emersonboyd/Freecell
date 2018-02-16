package cs3500.hw03;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import cs3500.hw02.model.Card;
import cs3500.hw03.mocks.FreecellModelMock;

import static cs3500.hw03.FreecellController.MESSAGE_GAME_OVER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FreecellControllerTest {
  private static final String UNSHUFFLED_STATE = "F1:\n" +
          "F2:\n" +
          "F3:\n" +
          "F4:\n" +
          "O1:\n" +
          "O2:\n" +
          "O3:\n" +
          "O4:\n" +
          "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦, 10♦\n" +
          "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠\n" +
          "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
          "C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣\n" +
          "C5: 5♦, K♦, 8♦, 3♦, J♦, 6♦\n" +
          "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
          "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
          "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";

  private static final String UNSHUFFLED_STATE_MOVE_1 = "F1:\n" +
          "F2:\n" +
          "F3:\n" +
          "F4:\n" +
          "O1: 10♦\n" +
          "O2:\n" +
          "O3:\n" +
          "O4:\n" +
          "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦\n" +
          "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠\n" +
          "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
          "C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣\n" +
          "C5: 5♦, K♦, 8♦, 3♦, J♦, 6♦\n" +
          "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
          "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
          "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";

  private static final String UNSHUFFLED_STATE_MOVE_2 = "F1:\n" +
          "F2:\n" +
          "F3:\n" +
          "F4:\n" +
          "O1: 10♦\n" +
          "O2: J♠\n" +
          "O3:\n" +
          "O4:\n" +
          "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦\n" +
          "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠\n" +
          "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
          "C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣\n" +
          "C5: 5♦, K♦, 8♦, 3♦, J♦, 6♦\n" +
          "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
          "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
          "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";

  public static final String EXPANDED_STATE_COMPLETE = "";

  Reader in;
  StringBuffer out;
  IFreecellController<Card> controller;

  FreecellOperations<Card> model1;
  List<Card> deck1;
  int numCascades1;
  int numOpens1;
  boolean shuffle1;

  /**
   * Setup the controller(s) as FreecellController instances for testing.
   */
  @Before
  public void setup() {
    this.in = new StringReader("");
    this.out = new StringBuffer();

    this.controller = new FreecellController(this.in, this.out);

    this.model1 = new FreecellModel();
    this.deck1 = model1.getDeck();
    this.numCascades1 = 8;
    this.numOpens1 = 4;
    this.shuffle1 = false;
  }

  /**
   * Sets up the controller to be fed input from a string and plays the game with that input.
   *
   * @param input the input to feed to the controller
   * @throws IllegalArgumentException if the controller throws it
   * @throws IOException              if the controller throws it
   */
  private void playGameWithInput(String input) throws IllegalArgumentException, IOException {
    this.in = new StringReader(input);
    this.out = new StringBuffer();

    this.controller = new FreecellController(this.in, this.out);
    controller.playGame(deck1, model1, numCascades1, numOpens1, shuffle1);
  }

  // Test creating a controller with a null Readable object
  @Test
  public void testConstructorErrorWithNullReadable() {
    try {
      controller = new FreecellController(null, this.out);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_NULL_READABLE_OR_APPENDABLE, e.getMessage());
    }
  }

  // Test creating a controller with a null Appendable object
  @Test
  public void testConstructorErrorWithNullAppendable() {
    try {
      controller = new FreecellController(this.in, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_NULL_READABLE_OR_APPENDABLE, e.getMessage());
    }
  }

  // Test creating a controller with null Readable and Appendable objects
  @Test
  public void testConstructorErrorWithNullReadableAndAppendable() {
    try {
      controller = new FreecellController(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_NULL_READABLE_OR_APPENDABLE, e.getMessage());
    }
  }

  // Test playGame with a null deck
  @Test
  public void testPlayGameWithNullDeck() {
    try {
      assertNotNull(model1);
      controller.playGame(null, model1, numCascades1, numOpens1, shuffle1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_NULL_DECK_OR_MODEL, e.getMessage());
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with a null model
  @Test
  public void testPlayGameWithNullModel() {
    try {
      assertNotNull(deck1);
      controller.playGame(deck1, null, numCascades1, numOpens1, shuffle1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_NULL_DECK_OR_MODEL, e.getMessage());
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with an invalid numCascade parameter
  @Test
  public void testPlayGameWithInvalidNumCascade() {
    try {
      controller.playGame(deck1, model1, 0, numOpens1, shuffle1);
      assertEquals(FreecellController.WARNING_INVALID_START_GAME_PARAMS, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with an invalid numOpen parameter
  @Test
  public void testPlayGameWithInvalidNumOpen() {
    try {
      controller.playGame(deck1, model1, numCascades1, 0, shuffle1);
      assertEquals(FreecellController.WARNING_INVALID_START_GAME_PARAMS, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with a lowercase Q
  @Test
  public void testPlayGameQuitPrematurelyLowercase() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with an uppercase Q
  @Test
  public void testPlayGameQuitPrematurelyUppercase() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("Q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with two invalid first commands
  @Test
  public void testPlayGameInvalidFirstCommand() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_INVALID_FROM_PILE_TYPE_AND_NUMBER_COMMAND
            + FreecellController.WARNING_INVALID_FROM_PILE_TYPE_AND_NUMBER_COMMAND
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C2e f4 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with an invalid second command
  @Test
  public void testPlayGameInvalidSecondCommand() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_INVALID_FROM_CARD_INDEX_COMMAND
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C2 F1 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with an invalid third command
  @Test
  public void testPlayGameInvalidThirdCommand() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_INVALID_TO_PILE_TYPE_AND_NUMBER_COMMAND
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C2 4 W2 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with a valid command set but error thrown from the model
  @Test
  public void testPlayGameErrorForwardedFromModelGeneral() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL
            + FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C2 4 O1 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame forwarding an error from the model by moving from open pile 0
  @Test
  public void testPlayGameErrorForwardedFromModelOpenPileZero() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL
            + FreecellModel.ERROR_OPEN_PILE_NUMBER_OUT_OF_BOUNDS
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("O0 1 O2 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame forwarding an error from the model by moving from cascade pile 9
  @Test
  public void testPlayGameErrorForwardedFromModelCascadePileNine() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL
            + FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C3 7 C9 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame forwarding an error from the model by moving from card index 0
  @Test
  public void testPlayGameErrorForwardedFromModelCardIndexZero() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL
            + FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C3 0 C9 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame called twice
  @Test
  public void testPlayGameCalledTwice() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY
            + UNSHUFFLED_STATE
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("q");
      this.in.reset();
      controller.playGame(deck1, model1, numCascades1, numOpens1, shuffle1);
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame once the game has finished
  @Test
  public void testPlayGameGameOver() {
    String expected = UNSHUFFLED_STATE
            + MESSAGE_GAME_OVER;

    try {
      this.in = new StringReader("");
      this.out = new StringBuffer();

      this.controller = new FreecellController(this.in, this.out);
      controller.playGame(deck1, new FreecellModelMock(), numCascades1, numOpens1, shuffle1);

      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with one move of the game state
  @Test
  public void testPlayGameOneMove() {
    String expected = UNSHUFFLED_STATE
            + "\n"
            + UNSHUFFLED_STATE_MOVE_1
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C1 7 O1 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with two moves of the game state
  @Test
  public void testPlayGameTwoMoves() {
    String expected = UNSHUFFLED_STATE
            + "\n"
            + UNSHUFFLED_STATE_MOVE_1
            + "\n"
            + UNSHUFFLED_STATE_MOVE_2
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C1 7 O1\n" +
              "C2 7 O2 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with a valid move, a series of invalid moves, and then a valid move
  @Test
  public void testPlayGameTwoMovesWithInvalidMovesBetween() {
    String expected = UNSHUFFLED_STATE
            + "\n"
            + UNSHUFFLED_STATE_MOVE_1
            + FreecellController.WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL
            + FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS
            + FreecellController.WARNING_MOVE_ERROR_FORWARDED_FROM_MODEL
            + FreecellModel.ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS
            + "\n"
            + UNSHUFFLED_STATE_MOVE_2
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C1 7 O1\n" +
              "C4 10 F3\n" +
              "C-10 3 O2\n" +
              "C2 7 O2 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with multiple invalid inputs before a valid input
  @Test
  public void testPlayGameInvalidInputsThenValidInputs() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_INVALID_FROM_PILE_TYPE_AND_NUMBER_COMMAND
            + FreecellController.WARNING_INVALID_FROM_PILE_TYPE_AND_NUMBER_COMMAND
            + FreecellController.WARNING_INVALID_TO_PILE_TYPE_AND_NUMBER_COMMAND
            + "\n"
            + UNSHUFFLED_STATE_MOVE_1
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("bad bad C1 7 bad O1 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test playGame with commas separating each command
  @Test
  public void testPlayGameInvalidInputsWithCommas() {
    String expected = UNSHUFFLED_STATE
            + FreecellController.WARNING_INVALID_FROM_PILE_TYPE_AND_NUMBER_COMMAND
            + FreecellController.MESSAGE_GAME_QUIT_PREMATURELY;

    try {
      playGameWithInput("C1,7,O1 q");
      assertEquals(expected, this.out.toString());
    } catch (IllegalArgumentException e) {
      fail();
    } catch (IOException e) {
      fail();
    }
  }

  // Test isValidPile with an uppercase C and valid number
  @Test
  public void testIsValidPileUppercaseCAndValidNumber() {
    assertTrue(FreecellController.isValidPile("C14"));
  }

  // Test isValidPile with an uppercase F and valid number
  @Test
  public void testIsValidPileUppercaseFAndValidNumber() {
    assertTrue(FreecellController.isValidPile("F0"));
  }

  // Test isValidPile with an uppercase O and valid number
  @Test
  public void testIsValidPileUppercaseOAndValidNumber() {
    assertTrue(FreecellController.isValidPile("O9"));
  }

  // Test isValidPile with a lowercase C and valid number
  @Test
  public void testIsValidPileLowercaseCAndValidNumber() {
    assertFalse(FreecellController.isValidPile("c9"));
  }

  // Test isValidPile with a lowercase F and valid number
  @Test
  public void testIsValidPileLowercaseFAndValidNumber() {
    assertFalse(FreecellController.isValidPile("f9"));
  }

  // Test isValidPile with a lowercase O and valid number
  @Test
  public void testIsValidPileLowercaseOAndValidNumber() {
    assertFalse(FreecellController.isValidPile("o9"));
  }

  // Test isValidPile with an uppercase C and invalid number
  @Test
  public void testIsValidPileUppercaseCAndInvalidNumber() {
    assertFalse(FreecellController.isValidPile("Cj3"));
  }

  // Test isValidInteger with a large number
  @Test
  public void testIsValidIntegerWithALotOfDigits() {
    assertTrue(FreecellController.isValidInteger("30195"));
  }

  // Test isValidInteger with a 2 digit number
  @Test
  public void testIsValidIntegerWithTwoDigits() {
    assertTrue(FreecellController.isValidInteger("13"));
  }

  // Test isValidInteger with a 0
  @Test
  public void testIsValidIntegerWithAZero() {
    assertTrue(FreecellController.isValidInteger("0"));
  }

  // Test isValidInteger with a 9
  @Test
  public void testIsValidIntegerWithANine() {
    assertTrue(FreecellController.isValidInteger("9"));
  }

  // Test isValidInteger with a negative number
  @Test
  public void testIsValidIntegerWithANegativeNumber() {
    assertTrue(FreecellController.isValidInteger("-9"));
  }

  // Test isValidInteger with a decimal
  @Test
  public void testIsValidIntegerWithADecimal() {
    assertFalse(FreecellController.isValidInteger("23.45"));
  }

  // Test isValidInteger with text at the beginning
  @Test
  public void testIsValidIntegerWithTextAtBeginning() {
    assertFalse(FreecellController.isValidInteger("d456"));
  }

  // Test isValidInteger with text at the middle
  @Test
  public void testIsValidIntegerWithTextAtMiddle() {
    assertFalse(FreecellController.isValidInteger("4d56"));
  }

  // Test isValidInteger with text at the end
  @Test
  public void testIsValidIntegerWithTextAtEnd() {
    assertFalse(FreecellController.isValidInteger("445ge"));
  }

  // Test parsePileType with an uppercase C
  @Test
  public void testParsePileTypeUppercaseC() {
    try {
      assertEquals(PileType.CASCADE, FreecellController.parsePileType('C'));
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  // Test parsePileType with an uppercase F
  @Test
  public void testParsePileTypeUppercaseF() {
    try {
      assertEquals(PileType.FOUNDATION, FreecellController.parsePileType('F'));
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  // Test parsePileType with an uppercase O
  @Test
  public void testParsePileTypeUppercaseO() {
    try {
      assertEquals(PileType.OPEN, FreecellController.parsePileType('O'));
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  // Test parsePileType with a lowercase C
  @Test
  public void testParsePileTypeLowercaseC() {
    try {
      FreecellController.parsePileType('c');
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_INVALID_PILETYPE_CHARACTER, e.getMessage());
    }
  }

  // Test parsePileType with a lowercase F
  @Test
  public void testParsePileTypeLowercaseF() {
    try {
      FreecellController.parsePileType('f');
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_INVALID_PILETYPE_CHARACTER, e.getMessage());
    }
  }

  // Test parsePileType with a lowercase O
  @Test
  public void testParsePileTypeLowercaseO() {
    try {
      FreecellController.parsePileType('o');
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellController.ERROR_INVALID_PILETYPE_CHARACTER, e.getMessage());
    }
  }
}