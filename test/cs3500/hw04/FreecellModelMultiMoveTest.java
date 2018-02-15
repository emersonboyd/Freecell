package cs3500.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FreecellModelMultiMoveTest {
  FreecellModelMultiMove model1;

  // set up the two models as new FreecellModel instances
  @Before
  public void setup() {
    model1 = new FreecellModelMultiMove();

    setupPiles1();
  }

  // setup piles of cards in model1 to be used for testing
  private void setupPiles1() {
//    List<Card> deck = Arrays.asList(new Card(Suit.SPADES, Value.ACE),
//            new Card(Suit.CLUBS, Value.THREE),
//            new Card(Suit.HEARTS, Value.QUEEN),
//            new Card(Suit.DIAMONDS, Value.EIGHT),
//            new Card(Suit.HEARTS, Value.JACK),
//            new Card(Suit.DIAMONDS, Value.KING),
//            new Card(Suit.SPADES, Value.QUEEN),
//            new Card(Suit.SPADES, Value.ACE),)

    model1.startGame(model1.getDeck(), 8, 4, false);

    // Sets up piles like this:
    //    F1:
    //    F2:
    //    F3:
    //    F4:
    //    O1:
    //    O2:
    //    O3:
    //    O4:
    //    C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦, 10♦
    //    C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠
    //    C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥
    //    C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣
    //    C5: 5♦, K♦, 8♦, 3♦, J♦, 6♦
    //    C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠
    //    C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥
    //    C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣

    // Sets up piles like this:
    //    "F1: A♠\n" +
    //    "F2: A♣, 2♣\n" +
    //    "F3: A♥, 2♥\n" +
    //    "F4:\n" +
    //    "O1:\n" +
    //    "O2: 3♣\n" +
    //    "O3: Q♥\n" +
    //    "O4: 8♦\n" +
    //    "C1: J♥\n" +
    //    "C2: K♦, Q♣, J♦, 10♣, 9♥, 8♠, 7♦, 6♣, 5♥, 4♠, 3♥\n" +
    //    "C3: Q♠\n" +
    //    "C4: 10♠, 9♠\n" +
    //    "C5: 6♠\n" +
    //    "C6:\n" +
    //    "C7:\n" +
    //    "C8: 7♣");
  }

  // Test that an invalid move does not change any of the piles
  @Test
  public void testMoveDoesNotAlterPiles() {
    String expected = "F1:\n" +
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
            "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";try {
      model1.move(PileType.OPEN, 3, 0, PileType.CASCADE, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(expected, model1.getGameState());
    }
  }

  // Test moving cards before the game has started -- illegal
  @Test
  public void testMoveBeforeGameStart() {
    model1 = new FreecellModelMultiMove();try {
      model1.move(PileType.FOUNDATION, 4, 6, PileType.OPEN, 4);
      fail();
    } catch (IllegalStateException e) {
      assertEquals(FreecellModel.ERROR_MOVE_BEFORE_GAME_START, e.getMessage());
    }
  }

  // Test moving a card to an open pile that has a card -- illegal
  @Test
  public void testMoveToAlreadyFilledOpenPile() {
    try {
      model1.move(PileType.CASCADE, 1, 6, PileType.OPEN, 3);
      model1.move(PileType.CASCADE, 1, 5, PileType.OPEN, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_OPEN_PILE_ALREADY_FILLED, e.getMessage());
    }
  }

  // Test moving a card to a cascade pile that is the wrong color -- illegal
  @Test
  public void testMoveToCascadeWrongColor() {
    try {
      model1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
      model1.move(PileType.CASCADE, 0, 5, PileType.OPEN, 1);
      model1.move(PileType.CASCADE, 0, 4, PileType.CASCADE, 6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModelMultiMove.ERROR_CANNOT_INSERT_BUILD_TO_DESTINATION_CASCADE,
              e.getMessage());
    }
  }

  // Test moving a card to a cascade pile that is the wrong card value -- illegal
  @Test
  public void testMoveToCascadeWrongValue() {
    try {
      model1.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModelMultiMove.ERROR_CANNOT_INSERT_BUILD_TO_DESTINATION_CASCADE,
              e.getMessage());
    }
  }

  // Test moving a card to a foundation pile that is the wrong card suit -- illegal
  @Test
  public void testMoveToFoundationWrongSuit() {
    try {
      model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
      model1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
      model1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 1);
      model1.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_MISMATCH_SUIT_IN_THIS_FOUNDATION, e.getMessage());
    }
  }

  // Test moving a card to a foundation pile that is the wrong card value -- illegal
  @Test
  public void testMoveToFoundationWrongValue() {
    try {
      model1.move(PileType.CASCADE, 1, 6, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_MISMATCH_VALUE_IN_THIS_FOUNDATION, e.getMessage());
    }
  }

  // Test moving a non-ace card to an open foundation pile -- illegal
  @Test
  public void testMoveToFoundationDuplicateSuit() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
      model1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
      model1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 1);
      model1.move(PileType.CASCADE, 3, 5, PileType.OPEN, 2);
      model1.move(PileType.CASCADE, 3, 4, PileType.OPEN, 3);
      model1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_MISMATCH_VALUE_IN_THIS_FOUNDATION, e.getMessage());
    }
  }

  // Test moving a card from a foundation pile after it's been placed -- illegal
  @Test
  public void testMoveFromFoundationPile() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 3);
      model1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
      model1.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 5);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_MOVE_FROM_FOUNDATION_PILE, e.getMessage());
    }
  }

  // Test moving from a cascade pile where the pileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveFromCascadePileNumberTooHigh() {
try {
      model1.move(PileType.CASCADE, 8, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving from a cascade pile where the pileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveFromCascadePileNumberTooLow() {
try {
      model1.move(PileType.CASCADE, -1, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving from a cascade pile where the cardIndex is out of bounds too high -- illegal
  @Test
  public void testMoveFromCascadeCardIndexTooHigh() {
try {
      model1.move(PileType.CASCADE, 1, 7, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving from a cascade pile where the cardIndex is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveFromCascadeCardIndexTooLow() {
try {
      model1.move(PileType.CASCADE, 1, -1, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving from an open pile where the pileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveFromOpenPileNumberTooHigh() {
try {
      model1.move(PileType.OPEN, 4, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_OPEN_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving from an open pile where the pileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveFromOpenPileNumberTooLow() {
try {
      model1.move(PileType.OPEN, -1, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_OPEN_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving from an open pile where the cardIndex is out of bounds too high -- illegal
  @Test
  public void testMoveFromOpenCardIndexTooHigh() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 3);
      model1.move(PileType.OPEN, 3, 1, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving from an open pile where the cardIndex is out of bounds too low -- illegal
  @Test
  public void testMoveFromOpenCardIndexTooLow() {
try {
      model1.move(PileType.OPEN, 3, -1, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving to a cascade pile where the destPileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveToCascadePileNumberTooHigh() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 8);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving to a cascade pile where the destPileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveToCascadePileNumberTooLow() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.CASCADE, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving to an open pile where the destPileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveToOpenPileNumberTooHigh() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving to an open pile where the destPileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveToOpenPileNumberTooLow() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving to a foundation pile where the destPileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveToFoundationPileNumberTooHigh() {
try {
      model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
      model1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving to a foundation pile where the destPileNumber is out of bounds too low -- illegal
  @Test
  public void testMoveToFoundationPileNumberTooLow() {
try {
      model1.move(PileType.CASCADE, 1, 6, PileType.FOUNDATION, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS, e.getMessage());
    }
  }

  // Test moving a card from a cascade to an open pile -- legal
  @Test
  public void testMoveFromCascadePileToOpenPile() {
    String expected = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 6♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠\n" +
            "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
            "C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣\n" +
            "C5: 5♦, K♦, 8♦, 3♦, J♦\n" +
            "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
            "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
            "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";

    model1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);

    assertEquals(expected, model1.getGameState());
  }

  // Test moving a card from a cascade to a cascade pile -- legal
  @Test
  public void testMoveFromCascadePileToCascadePile() {
    String expected = "F1:\n" +
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
            "C5: 5♦, K♦, 8♦, 3♦, J♦\n" +
            "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠, 6♦\n" +
            "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
            "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";

    model1.move(PileType.CASCADE, 4, 5, PileType.CASCADE, 5);

    assertEquals(expected, model1.getGameState());
  }

  // Test moving a card from a cascade to a foundation pile -- legal
  @Test
  public void testMoveFromCascadePileToFoundationPile() {
    String expected = "F1: A♣, 2♣\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♣\n" +
            "O2: K♣\n" +
            "O3: 5♣\n" +
            "O4: 10♣\n" +
            "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠\n" +
            "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
            "C4: 4♣, Q♣, 7♣\n" +
            "C5: 5♦, K♦, 8♦, 3♦, J♦, 6♦\n" +
            "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
            "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
            "C8: 8♣, 3♣, J♣, 6♣";

    model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 0);
    model1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    model1.move(PileType.CASCADE, 3, 6, PileType.OPEN, 1);
    model1.move(PileType.CASCADE, 3, 5, PileType.OPEN, 2);
    model1.move(PileType.CASCADE, 3, 4, PileType.OPEN, 3);
    model1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION, 0);

    assertEquals(expected, model1.getGameState());
  }

  // Test moving a card from an open pile to an open pile -- legal
  @Test
  public void testMoveFromOpenPileToOpenPile() {
    String expected = "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 6♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠\n" +
            "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
            "C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣\n" +
            "C5: 5♦, K♦, 8♦, 3♦, J♦\n" +
            "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
            "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
            "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";

    model1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 2);
    model1.move(PileType.OPEN, 2, 0, PileType.OPEN, 0);

    assertEquals(expected, model1.getGameState());
  }


  // Test moving a card from an open pile to a cascade pile -- legal
  @Test
  public void testMoveFromOpenPileToCascadePile() {
    String expected = "F1:\n" +
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
            "C5: 5♦, K♦, 8♦, 3♦, J♦\n" +
            "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠, 6♦\n" +
            "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
            "C8: 8♣, 3♣, J♣, 6♣, A♣, 9♣";

    model1.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
    model1.move(PileType.OPEN, 0, 0, PileType.CASCADE, 5);

    assertEquals(expected, model1.getGameState());
  }

  @Test
  public void testMoveFromOpenPileToFoundationPile() {
    String expected = "F1: A♣\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4: 9♣\n" +
            "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠\n" +
            "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
            "C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣\n" +
            "C5: 5♦, K♦, 8♦, 3♦, J♦, 6♦\n" +
            "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
            "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
            "C8: 8♣, 3♣, J♣, 6♣";

    model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 3);
    model1.move(PileType.CASCADE, 7, 4, PileType.OPEN, 0);
    model1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);

    assertEquals(expected, model1.getGameState());
  }

  // Test moving any ace to an empty foundation pile -- legal
  @Test
  public void testMoveAceToFoundationPile() {
    String expected = "F1: A♣\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4: 9♣\n" +
            "C1: A♦, 9♦, 4♦, Q♦, 7♦, 2♦, 10♦\n" +
            "C2: 2♠, 10♠, 5♠, K♠, 8♠, 3♠, J♠\n" +
            "C3: 3♥, J♥, 6♥, A♥, 9♥, 4♥, Q♥\n" +
            "C4: 4♣, Q♣, 7♣, 2♣, 10♣, 5♣, K♣\n" +
            "C5: 5♦, K♦, 8♦, 3♦, J♦, 6♦\n" +
            "C6: 6♠, A♠, 9♠, 4♠, Q♠, 7♠\n" +
            "C7: 7♥, 2♥, 10♥, 5♥, K♥, 8♥\n" +
            "C8: 8♣, 3♣, J♣, 6♣";

    model1.move(PileType.CASCADE, 7, 5, PileType.OPEN, 3);
    model1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);

    assertEquals(expected, model1.getGameState());
  }

  // Test moving a card from the same spot to the same spot -- legal???
  @Test
  public void testMoveFromSameSpotToSameSpot() {
    String expected = "F1:\n" +
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

    model1.move(PileType.CASCADE, 1, 6, PileType.CASCADE, 1);

    assertEquals(expected, model1.getGameState());
  }


  // testMove with a cardIndex greater than the cascade pile size -- illegal
  // testMove with a cardIndex smaller than 0 -- illegal
  // testMove with a sourcePile that is an invalid build -- illegal
  // testMove with a destination cascade pile that doesn't accept the build suit -- illegal
  // testMove with a destination cascade pile that doesn't accept the build value -- illegal
  // testMove with a destination open pile that doesn't accept more than one card -- illegal
  // testMove with a destination foundation pile that doesn't accept more than one card -- illegal
  // testMove with multiple valid cards move to a filled cascade pile -- legal
  // testMove with multiple valid cards move to an empty cascade pile -- legal
  // testMove with a single card to a foundation pile -- legal
  // testMove with a single card to an open pile -- legal
  // testMove with a single card to a cascade pile -- legal
  // testMove with all of the valid tests remaining from previous tests -- legal
  // testMove with too few available open/cascade piles (by 1 too few) -- illegal
  // testMove with any sections that i got points off of on the self eval from hw02

  // possible failure is if the same move to the same location fails
  // possible failure is if the calculation for number of movable cards is wrong
}
