package cs3500.hw02;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.hw02.model.Card;
import cs3500.hw02.model.Suit;
import cs3500.hw02.model.Value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FreecellModelTest {
  FreecellModel model1;
  FreecellModel model2;

  // set up the two models as new FreecellModel instances
  @Before
  public void setup() {
    model1 = new FreecellModel();
    model2 = new FreecellModel();
  }

  // setup piles of cards in model1 to be used for testing
  private void setupPiles1() {
    model1.startGame(model1.getDeck(), 8, 4, true);

    List<Card> foundationPile1 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.SPADES, Value.ACE)));
    List<Card> foundationPile2 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.CLUBS, Value.ACE),
                    new Card(Suit.CLUBS, Value.TWO)));
    List<Card> foundationPile3 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.HEARTS, Value.ACE),
                    new Card(Suit.HEARTS, Value.TWO)));
    List<Card> foundationPile4 = new ArrayList<Card>();
    model1.foundationPiles = new ArrayList<List<Card>>(
            Arrays.asList(foundationPile1, foundationPile2, foundationPile3, foundationPile4));

    List<Card> openPile1 = new ArrayList<Card>();
    List<Card> openPile2 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.CLUBS, Value.THREE)));
    List<Card> openPile3 = new ArrayList<Card>();
    List<Card> openPile4 = new ArrayList<Card>(Arrays.asList(new Card(Suit.DIAMONDS, Value.EIGHT)));
    model1.openPiles = new ArrayList<List<Card>>(
            Arrays.asList(openPile1, openPile2, openPile3, openPile4));

    List<Card> cascadePile1 = new ArrayList<Card>();
    List<Card> cascadePile2 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.CLUBS, Value.FOUR),
                    new Card(Suit.CLUBS, Value.FIVE),
                    new Card(Suit.SPADES, Value.FOUR),
                    new Card(Suit.HEARTS, Value.THREE)));
    List<Card> cascadePile3 = new ArrayList<Card>();
    List<Card> cascadePile4 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.HEARTS, Value.TEN),
                    new Card(Suit.SPADES, Value.JACK),
                    new Card(Suit.DIAMONDS, Value.ACE)));
    List<Card> cascadePile5 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.SPADES, Value.SIX)));
    List<Card> cascadePile6 = new ArrayList<Card>();
    List<Card> cascadePile7 = new ArrayList<Card>();
    List<Card> cascadePile8 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.DIAMONDS, Value.SEVEN)));
    model1.cascadePiles = new ArrayList<List<Card>>(
            Arrays.asList(cascadePile1, cascadePile2, cascadePile3, cascadePile4,
                    cascadePile5, cascadePile6, cascadePile7, cascadePile8));

    // Sets up piles like this:
    //    "F1: A♠\n" +
    //    "F2: A♣, 2♣\n" +
    //    "F3: A♥, 2♥\n" +
    //    "F4:\n" +
    //    "O1:\n" +
    //    "O2: 3♣\n" +
    //    "O3:\n" +
    //    "O4: 8♦\n" +
    //    "C1:\n" +
    //    "C2: 4♣, 5♣, 4♠, 3♥\n" +
    //    "C3:\n" +
    //    "C4: 10♥, J♠, A♦\n" +
    //    "C5: 6♠\n" +
    //    "C6:\n" +
    //    "C7:\n" +
    //    "C8: 7♦");
  }

  // setup piles of cards in model2 to be used for testing
  private void setupPiles2() {
    model2.startGame(model2.getDeck(), 8, 4, true);

    List<Card> foundationPile1 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.SPADES, Value.ACE)));
    List<Card> foundationPile2 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.CLUBS, Value.ACE),
                    new Card(Suit.CLUBS, Value.TWO)));
    List<Card> foundationPile3 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.HEARTS, Value.ACE),
                    new Card(Suit.HEARTS, Value.TWO)));
    List<Card> foundationPile4 = new ArrayList<Card>();
    model2.foundationPiles = new ArrayList<List<Card>>(
            Arrays.asList(foundationPile1, foundationPile2, foundationPile3, foundationPile4));

    List<Card> openPile1 = new ArrayList<Card>();
    List<Card> openPile2 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.CLUBS, Value.THREE)));
    List<Card> openPile3 = new ArrayList<Card>();
    List<Card> openPile4 = new ArrayList<Card>(Arrays.asList(new Card(Suit.DIAMONDS, Value.EIGHT)));
    model2.openPiles = new ArrayList<List<Card>>(
            Arrays.asList(openPile1, openPile2, openPile3, openPile4));

    List<Card> cascadePile1 = new ArrayList<Card>();
    List<Card> cascadePile2 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.CLUBS, Value.FOUR),
                    new Card(Suit.CLUBS, Value.FIVE),
                    new Card(Suit.SPADES, Value.FOUR),
                    new Card(Suit.HEARTS, Value.THREE)));
    List<Card> cascadePile3 = new ArrayList<Card>();
    List<Card> cascadePile4 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.HEARTS, Value.TEN),
                    new Card(Suit.SPADES, Value.JACK),
                    new Card(Suit.DIAMONDS, Value.ACE)));
    List<Card> cascadePile5 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.SPADES, Value.SIX)));
    List<Card> cascadePile6 = new ArrayList<Card>();
    List<Card> cascadePile7 = new ArrayList<Card>();
    List<Card> cascadePile8 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.DIAMONDS, Value.SEVEN)));
    model2.cascadePiles = new ArrayList<List<Card>>(
            Arrays.asList(cascadePile1, cascadePile2, cascadePile3, cascadePile4,
                    cascadePile5, cascadePile6, cascadePile7, cascadePile8));

    // Sets up piles like this:
    //    "F1: A♠\n" +
    //    "F2: A♣, 2♣\n" +
    //    "F3: A♥, 2♥\n" +
    //    "F4:\n" +
    //    "O1:\n" +
    //    "O2: 3♣\n" +
    //    "O3:\n" +
    //    "O4: 8♦\n" +
    //    "C1:\n" +
    //    "C2: 4♣, 5♣, 4♠, 3♥\n" +
    //    "C3:\n" +
    //    "C4: 10♥, J♠, A♦\n" +
    //    "C5: 6♠\n" +
    //    "C6:\n" +
    //    "C7:\n" +
    //    "C8: 7♦");
  }

  // setup piles of cards in model1 to be used for testing
  private void setupGameOver1() {
    model1.startGame(model1.getDeck(), 8, 4, true);

    List<Card> foundationPile1 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.SPADES, Value.ACE),
                    new Card(Suit.SPADES, Value.TWO),
                    new Card(Suit.SPADES, Value.THREE),
                    new Card(Suit.SPADES, Value.FOUR),
                    new Card(Suit.SPADES, Value.FIVE),
                    new Card(Suit.SPADES, Value.SIX),
                    new Card(Suit.SPADES, Value.SEVEN),
                    new Card(Suit.SPADES, Value.EIGHT),
                    new Card(Suit.SPADES, Value.NINE),
                    new Card(Suit.SPADES, Value.TEN),
                    new Card(Suit.SPADES, Value.JACK),
                    new Card(Suit.SPADES, Value.QUEEN),
                    new Card(Suit.SPADES, Value.KING)));
    List<Card> foundationPile2 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.CLUBS, Value.ACE),
                    new Card(Suit.CLUBS, Value.TWO),
                    new Card(Suit.CLUBS, Value.THREE),
                    new Card(Suit.CLUBS, Value.FOUR),
                    new Card(Suit.CLUBS, Value.FIVE),
                    new Card(Suit.CLUBS, Value.SIX),
                    new Card(Suit.CLUBS, Value.SEVEN),
                    new Card(Suit.CLUBS, Value.EIGHT),
                    new Card(Suit.CLUBS, Value.NINE),
                    new Card(Suit.CLUBS, Value.TEN),
                    new Card(Suit.CLUBS, Value.JACK),
                    new Card(Suit.CLUBS, Value.QUEEN),
                    new Card(Suit.CLUBS, Value.KING)));
    List<Card> foundationPile3 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.HEARTS, Value.ACE),
                    new Card(Suit.HEARTS, Value.TWO),
                    new Card(Suit.HEARTS, Value.THREE),
                    new Card(Suit.HEARTS, Value.FOUR),
                    new Card(Suit.HEARTS, Value.FIVE),
                    new Card(Suit.HEARTS, Value.SIX),
                    new Card(Suit.HEARTS, Value.SEVEN),
                    new Card(Suit.HEARTS, Value.EIGHT),
                    new Card(Suit.HEARTS, Value.NINE),
                    new Card(Suit.HEARTS, Value.TEN),
                    new Card(Suit.HEARTS, Value.JACK),
                    new Card(Suit.HEARTS, Value.QUEEN),
                    new Card(Suit.HEARTS, Value.KING)));
    List<Card> foundationPile4 = new ArrayList<Card>(
            Arrays.asList(new Card(Suit.DIAMONDS, Value.ACE),
                    new Card(Suit.DIAMONDS, Value.TWO),
                    new Card(Suit.DIAMONDS, Value.THREE),
                    new Card(Suit.DIAMONDS, Value.FOUR),
                    new Card(Suit.DIAMONDS, Value.FIVE),
                    new Card(Suit.DIAMONDS, Value.SIX),
                    new Card(Suit.DIAMONDS, Value.SEVEN),
                    new Card(Suit.DIAMONDS, Value.EIGHT),
                    new Card(Suit.DIAMONDS, Value.NINE),
                    new Card(Suit.DIAMONDS, Value.TEN),
                    new Card(Suit.DIAMONDS, Value.JACK),
                    new Card(Suit.DIAMONDS, Value.QUEEN),
                    new Card(Suit.DIAMONDS, Value.KING)));
    model1.foundationPiles = new ArrayList<List<Card>>(
            Arrays.asList(foundationPile1, foundationPile2, foundationPile3, foundationPile4));

    List<Card> openPile1 = new ArrayList<Card>();
    List<Card> openPile2 = new ArrayList<Card>();
    List<Card> openPile3 = new ArrayList<Card>();
    List<Card> openPile4 = new ArrayList<Card>();
    model1.openPiles = new ArrayList<List<Card>>(
            Arrays.asList(openPile1, openPile2, openPile3, openPile4));

    List<Card> cascadePile1 = new ArrayList<Card>();
    List<Card> cascadePile2 = new ArrayList<Card>();
    List<Card> cascadePile3 = new ArrayList<Card>();
    List<Card> cascadePile4 = new ArrayList<Card>();
    List<Card> cascadePile5 = new ArrayList<Card>();
    List<Card> cascadePile6 = new ArrayList<Card>();
    List<Card> cascadePile7 = new ArrayList<Card>();
    List<Card> cascadePile8 = new ArrayList<Card>();
    model1.cascadePiles = new ArrayList<List<Card>>(
            Arrays.asList(cascadePile1, cascadePile2, cascadePile3, cascadePile4,
                    cascadePile5, cascadePile6, cascadePile7, cascadePile8));

    // Sets up piles like this:
    //    "F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
    //    "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
    //    "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
    //    "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
    //    "O1:\n" +
    //    "O2:\n" +
    //    "O3:\n" +
    //    "O4:\n" +
    //    "C1:\n" +
    //    "C2:\n" +
    //    "C3:\n" +
    //    "C4:\n" +
    //    "C5:\n" +
    //    "C6:\n" +
    //    "C7:\n" +
    //    "C8:");
  }

  // Test the getDeck method and check the size
  @Test
  public void testGetDeckSize() {
    assertEquals(model1.getDeck().size(), 52);
  }

  // Test the getDeck method without shuffling for index 0
  @Test
  public void testGetDeckIndex0() {
    assertEquals(model1.getDeck().get(0), new Card(Suit.DIAMONDS, Value.ACE));
  }

  // Test the getDeck method without shuffling for index 35
  @Test
  public void testGetDeckIndex35() {
    assertEquals(model1.getDeck().get(35), new Card(Suit.CLUBS, Value.TEN));
  }

  // Test the getDeck method without shuffling for index 51
  @Test
  public void testGetDeckIndex51() {
    assertEquals(model1.getDeck().get(51), new Card(Suit.CLUBS, Value.KING));
  }

  // Test the isDeckValid method after removing a card from a standard deck
  @Test
  public void testIsDeckValidMissingCards1() {
    List<Card> deck = model1.getDeck();
    deck.remove(51);

    assertFalse(model1.isDeckValid(deck));
  }

  // Test the isDeckValid method after removing two cards from a standard deck
  @Test
  public void testIsDeckValidMissingCards2() {
    List<Card> deck = model1.getDeck();
    deck.remove(0);
    deck.remove(35);

    assertFalse(model1.isDeckValid(deck));
  }

  // Test the isDeckValid method after adding another card to a standard deck
  @Test
  public void testIsDeckValidTooManyCards() {
    List<Card> deck = model1.getDeck();
    deck.add(new Card(Suit.DIAMONDS, Value.FIVE));

    assertFalse(model1.isDeckValid(deck));
  }

  // Test the isDeckValid method after duplicating a card within a standard deck
  @Test
  public void testIsDeckValidDuplicateCards1() {
    List<Card> deck = model1.getDeck();
    deck.remove(51);
    deck.add(new Card(Suit.HEARTS, Value.ACE));

    assertFalse(model1.isDeckValid(deck));
  }

  // Test the isDeckValid method after duplicating a card twice within a standard deck
  @Test
  public void testIsDeckValidDuplicateCards2() {
    List<Card> deck = model1.getDeck();
    deck.remove(50);
    deck.remove(50);
    deck.add(new Card(Suit.DIAMONDS, Value.FIVE));
    deck.add(new Card(Suit.DIAMONDS, Value.FIVE));

    assertFalse(model1.isDeckValid(deck));
  }

  // Test the isDeckValid method after calling the getDeck method
  @Test
  public void testIsDeckValidActuallyValid() {
    assertTrue(model1.isDeckValid(model1.getDeck()));
  }

  // Test that we trow an error when we pass an invalid deck to startGame
  @Test
  public void testStartGameError() {
    List<Card> deck = model1.getDeck();
    deck.remove(51);

    assertFalse(model1.isDeckValid(deck));

    try {
      model1.startGame(deck, 4, 4, false);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_DECK_INVALID);
    }
  }

  // Test that we create the right organization of cascade piles with 3 piles
  @Test
  public void testStartGameCascadePiles1() {
    model1.startGame(model1.getDeck(), 4, 4, false);

    assertEquals(model1.cascadePiles.size(), 4);
    assertEquals(model1.cascadePiles.get(0).size(), 13);
    assertEquals(model1.cascadePiles.get(2).size(), 13);
    assertEquals(model1.cascadePiles.get(0).get(0), new Card(Suit.DIAMONDS, Value.ACE));
    assertEquals(model1.cascadePiles.get(2).get(4), new Card(Suit.HEARTS, Value.SIX));
  }

  // Test that we create the right organization of cascade piles with 5 piles
  @Test
  public void testStartGameCascadePiles2() {
    model1.startGame(model1.getDeck(), 5, 4, false);

    assertEquals(model1.cascadePiles.size(), 5);
    assertEquals(model1.cascadePiles.get(1).size(), 11);
    assertEquals(model1.cascadePiles.get(2).size(), 10);
    assertEquals(model1.cascadePiles.get(3).get(7), new Card(Suit.HEARTS, Value.KING));
    assertEquals(model1.cascadePiles.get(1).get(10), new Card(Suit.CLUBS, Value.KING));
  }

  // Test that we create the right amount of open piles in startGame
  @Test
  public void testStartGameOpenPileSize() {
    model1.startGame(model1.getDeck(), 4, 7, false);

    assertEquals(model1.openPiles.size(), 7);
    assertEquals(model1.openPiles.get(6).size(), 0);
  }

  // Test that we create the right amount of foundation piles in startGame
  @Test
  public void testStartGameFoundationPileSize() {
    model1.startGame(model1.getDeck(), 6, 7, false);

    assertEquals(model1.foundationPiles.size(), 4);
    assertEquals(model1.foundationPiles.get(0).size(), 0);
  }

  // Test that the deck is not shuffled when we don't request it to be
  @Test
  public void testStartGameDeckNotShuffled() {
    model1.startGame(model1.getDeck(), 4, 4, false);
    model2.startGame(model2.getDeck(), 4, 4, false);

    assertEquals(model1.cascadePiles, model2.cascadePiles);
  }

  // Test that the deck is in fact shuffled when we request
  @Test
  public void testStartGameDeckShuffled() {
    model1.startGame(model1.getDeck(), 4, 4, false);
    model2.startGame(model2.getDeck(), 4, 4, true);

    assertNotEquals(model1.cascadePiles, model2.cascadePiles);
  }

  // Test that an error is thrown when there are too few cascade piles
  @Test
  public void testStartGameErrorNumCascadePiles() {
    try {
      model1.startGame(model1.getDeck(), 3, 4, false);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_PILE_NUM);
    }
  }

  // Test that an error is thrown when there are too few open piles
  @Test
  public void testStartGameErrorNumOpenPiles() {
    try {
      model1.startGame(model1.getDeck(), 4, 0, false);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_PILE_NUM);
    }
  }

  // Test the getGameState method before the game has started
  @Test
  public void testGetGameStateBeforeGameStart() {
    assertEquals(model1.getGameState(), "");
  }

  // Test the getGameState method with a predefined deck
  @Test
  public void testGetGameStateAfterGameStart() {
    setupPiles1();

    assertEquals(model1.getGameState(),
            "F1: A♠\n" +
                    "F2: A♣, 2♣\n" +
                    "F3: A♥, 2♥\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2: 3♣\n" +
                    "O3:\n" +
                    "O4: 8♦\n" +
                    "C1:\n" +
                    "C2: 4♣, 5♣, 4♠, 3♥\n" +
                    "C3:\n" +
                    "C4: 10♥, J♠, A♦\n" +
                    "C5: 6♠\n" +
                    "C6:\n" +
                    "C7:\n" +
                    "C8: 7♦");
  }

  // Test that an invalid move does not change any of the piles
  @Test
  public void testMoveDoesNotAlterPiles() {
    setupPiles1();

    model2.startGame(model2.getDeck(), 8, 4, true);

    model2.foundationPiles = new ArrayList<List<Card>>(model1.foundationPiles);
    model2.openPiles = new ArrayList<List<Card>>(model1.openPiles);
    model2.cascadePiles = new ArrayList<List<Card>>(model1.cascadePiles);

    try {
      model1.move(PileType.OPEN, 3, 0, PileType.CASCADE, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(model1.getGameState(), model2.getGameState());
    }
  }

  // Test moving cards before the game has started -- illegal
  @Test
  public void testMoveBeforeGameStart() {
    try {
      model1.move(PileType.FOUNDATION, 4, 6, PileType.OPEN, 4);
      fail();
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_MOVE_BEFORE_GAME_START);
    }
  }

  // Test moving a stack of cards to another cascade pile -- illegal
  @Test
  public void testMoveMultipleCards() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS);
    }
  }

  // Test moving a card to an open pile that has a card -- illegal
  @Test
  public void testMoveToAlreadyFilledOpenPile() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 3, 2, PileType.OPEN, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_OPEN_PILE_ALREADY_FILLED);
    }
  }

  // Test moving a card to a cascade pile that is the wrong color -- illegal
  @Test
  public void testMoveToCascadeWrongColor() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 3, 2, PileType.CASCADE, 7);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_MISMATCH_CARD_IN_THIS_CASCADE);
    }
  }

  // Test moving a card to a cascade pile that is the wrong card value -- illegal
  @Test
  public void testMoveToCascadeWrongValue() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 7);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_MISMATCH_CARD_IN_THIS_CASCADE);
    }
  }

  // Test moving a card to a foundation pile that is the wrong card suit -- illegal
  @Test
  public void testMoveToFoundationWrongSuit() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_MISMATCH_SUIT_IN_THIS_FOUNDATION);
    }
  }

  // Test moving a card to a foundation pile that is the wrong card value -- illegal
  @Test
  public void testMoveToFoundationWrongValue() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_MISMATCH_VALUE_IN_THIS_FOUNDATION);
    }
  }

  // Test moving a non-ace card to an open foundation pile -- illegal
  @Test
  public void testMoveToFoundationDuplicateSuit() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_MISMATCH_VALUE_IN_THIS_FOUNDATION);
    }
  }

  // Test moving a card from a foundation pile after it's been placed -- illegal
  @Test
  public void testMoveFromFoundationPile() {
    setupPiles1();

    try {
      model1.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 5);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_MOVE_FROM_FOUNDATION_PILE);
    }
  }

  // Test moving from a cascade pile where the pileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveFromCascadePileNumberTooHigh() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 8, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving from a cascade pile where the pileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveFromCascadePileNumberTooLow() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, -1, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_CASCADE_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving from a cascade pile where the cardIndex is out of bounds too high -- illegal
  @Test
  public void testMoveFromCascadeCardIndexTooHigh() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 4, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS);
    }
  }

  // Test moving from a cascade pile where the cardIndex is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveFromCascadeCardIndexTooLow() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, -1, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS);
    }
  }

  // Test moving from an open pile where the pileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveFromOpenPileNumberTooHigh() {
    setupPiles1();

    try {
      model1.move(PileType.OPEN, 4, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_OPEN_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving from an open pile where the pileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveFromOpenPileNumberTooLow() {
    setupPiles1();

    try {
      model1.move(PileType.OPEN, -1, 0, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_OPEN_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving from an open pile where the cardIndex is out of bounds too high -- illegal
  @Test
  public void testMoveFromOpenCardIndexTooHigh() {
    setupPiles1();

    try {
      model1.move(PileType.OPEN, 3, 1, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS);
    }
  }

  // Test moving from an open pile where the cardIndex is out of bounds too low -- illegal
  @Test
  public void testMoveFromOpenCardIndexTooLow() {
    setupPiles1();

    try {
      model1.move(PileType.OPEN, 3, -1, PileType.OPEN, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_CARD_INDEX_OUT_OF_BOUNDS);
    }
  }

  // Test moving to a cascade pile where the destPileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveToCascadePileNumberTooHigh() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 8);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving to a cascade pile where the destPileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveToCascadePileNumberTooLow() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.CASCADE, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving to an open pile where the destPileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveToOpenPileNumberTooHigh() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.OPEN, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving to an open pile where the destPileNumber is out of bounds too low (<0) -- illegal
  @Test
  public void testMoveToOpenPileNumberTooLow() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.OPEN, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving to a foundation pile where the destPileNumber is out of bounds too high -- illegal
  @Test
  public void testMoveToFoundationPileNumberTooHigh() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving to a foundation pile where the destPileNumber is out of bounds too low -- illegal
  @Test
  public void testMoveToFoundationPileNumberTooLow() {
    setupPiles1();

    try {
      model1.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), FreecellModel.ERROR_DEST_PILE_NUMBER_OUT_OF_BOUNDS);
    }
  }

  // Test moving a card from a cascade to an open pile -- legal
  @Test
  public void testMoveFromCascadePileToOpenPile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.cascadePiles.get(4);
    Card sourceCard = sourceList.remove(0);
    List<Card> destList = model2.openPiles.get(0);
    destList.add(sourceCard);

    model1.move(PileType.CASCADE, 4, 0, PileType.OPEN, 0);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  // Test moving a card from a cascade to a cascade pile -- legal
  @Test
  public void testMoveFromCascadePileToCascadePile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.cascadePiles.get(4);
    Card sourceCard = sourceList.remove(0);
    List<Card> destList = model2.cascadePiles.get(7);
    destList.add(sourceCard);

    model1.move(PileType.CASCADE, 4, 0, PileType.CASCADE, 7);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  // Test moving a card from a cascade to a foundation pile -- legal
  @Test
  public void testMoveFromCascadePileToFoundationPile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.cascadePiles.get(1);
    Card sourceCard = sourceList.remove(3);
    List<Card> destList = model2.foundationPiles.get(2);
    destList.add(sourceCard);

    model1.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, 2);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  // Test moving a card from an open pile to an open pile -- legal
  @Test
  public void testMoveFromOpenPileToOpenPile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.openPiles.get(3);
    Card sourceCard = sourceList.remove(0);
    List<Card> destList = model2.openPiles.get(2);
    destList.add(sourceCard);

    model1.move(PileType.OPEN, 3, 0, PileType.OPEN, 2);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  // Test moving a card from an open pile to a cascade pile -- legal
  @Test
  public void testMoveFromOpenPileToCascadePile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.openPiles.get(3);
    Card sourceCard = sourceList.remove(0);
    List<Card> destList = model2.cascadePiles.get(0);
    destList.add(sourceCard);

    model1.move(PileType.OPEN, 3, 0, PileType.CASCADE, 0);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  @Test
  public void testMoveFromOpenPileToFoundationPile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.openPiles.get(1);
    Card sourceCard = sourceList.remove(0);
    List<Card> destList = model2.foundationPiles.get(1);
    destList.add(sourceCard);

    model1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 1);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  @Test
  public void testMoveFromFoundationPileToOpenPile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.openPiles.get(1);
    Card sourceCard = sourceList.remove(0);
    List<Card> destList = model2.foundationPiles.get(1);
    destList.add(sourceCard);

    model1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 1);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  // Test moving any ace to an empty foundation pile -- legal
  @Test
  public void testMoveAceToFoundationPile() {
    setupPiles1();
    setupPiles2();

    List<Card> sourceList = model2.cascadePiles.get(3);
    Card sourceCard = sourceList.remove(2);
    List<Card> destList = model2.foundationPiles.get(3);
    destList.add(sourceCard);

    model1.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION, 3);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  // Test moving a card from the same spot to the same spot -- legal???
  @Test
  public void testMoveFromSameSpotToSameSpot() {
    setupPiles1();
    setupPiles2();

    model1.move(PileType.CASCADE, 1, 3, PileType.CASCADE, 1);

    assertEquals(model1.getGameState(), model2.getGameState());
  }

  // Test isGameOver when there is a stack with poorly-ordered values
  @Test
  public void testIsGameOverPoorlyOrderedValues() {
    setupGameOver1();

    List<Card> foundationPile1 = model1.foundationPiles.get(0);
    Card sourceCard = foundationPile1.remove(3);
    foundationPile1.add(sourceCard);

    assertFalse(model1.isGameOver());
  }

  // Test isGameOver when there is a stack with mixed suits
  @Test
  public void testIsGameOverMixedSuits() {
    setupGameOver1();

    List<Card> foundationPile1 = model1.foundationPiles.get(0);
    foundationPile1.remove(3);
    foundationPile1.add(3, new Card(Suit.HEARTS, Value.FOUR));

    assertFalse(model1.isGameOver());
  }

  // Test isGameOver when there is a stack with less than thirteen cards
  @Test
  public void testIsGameOverLessThanThirteenCards() {
    setupGameOver1();

    List<Card> foundationPile3 = model1.foundationPiles.get(2);
    foundationPile3.remove(12);

    assertFalse(model1.isGameOver());
  }

  // Test isGameOver when there is a stack with more than thirteen cards
  @Test
  public void testIsGameOverMoreThanThirteenCards() {
    setupGameOver1();

    List<Card> foundationPile3 = model1.foundationPiles.get(2);
    foundationPile3.add(new Card(Suit.HEARTS, Value.KING));

    assertFalse(model1.isGameOver());
  }

  // Test isGameOver when there are cards in the open piles
  @Test
  public void testIsGameOverWithFilledOpenPiles() {
    setupGameOver1();

    List<Card> openPile3 = model1.openPiles.get(2);
    openPile3.add(new Card(Suit.HEARTS, Value.KING));

    assertFalse(model1.isGameOver());
  }

  // Test isGameOver when there are cards in the cascade piles
  @Test
  public void testIsGameOverWithFilledCascadePiles() {
    setupGameOver1();

    List<Card> cascadePile3 = model1.cascadePiles.get(2);
    cascadePile3.add(new Card(Suit.HEARTS, Value.KING));

    assertFalse(model1.isGameOver());
  }

  // Test isGameOver for a valid set of gameOver suits/values
  @Test
  public void testIsGameOverWithValidSuitsAndValues1() {
    setupGameOver1();

    assertTrue(model1.isGameOver());
  }

  // Test isGameOver for a valid set of gameOver suits/values in a different suit order
  @Test
  public void testIsGameOverWithValidSuitsAndValues2() {
    setupGameOver1();

    List<Card> foundationPile2 = model1.foundationPiles.get(1);
    List<Card> foundationPile4 = model1.foundationPiles.get(3);
    model1.foundationPiles.set(1, foundationPile4);
    model1.foundationPiles.set(3, foundationPile2);

    assertTrue(model1.isGameOver());
  }
}