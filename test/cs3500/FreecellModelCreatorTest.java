package cs3500;

import org.junit.Test;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.model.Card;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.FreecellModelMultiMove;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FreecellModelCreatorTest {
  // Test create to receive an instance of FreecellModel when expected.
  @Test
  public void testCreateFreecellModel() {
    FreecellOperations<Card> model =
            FreecellModelCreator.create(FreecellModelCreator.GameType.SINGLEMOVE);

    assertTrue(model instanceof FreecellModel);
    assertFalse(model instanceof FreecellModelMultiMove);
  }

  // Test create to receive an instance of FreecellModelMultiMove when expected.
  @Test
  public void testCreateFreecellModelMultiMove() {
    FreecellOperations<Card> model =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);

    assertTrue(model instanceof FreecellModel);
    assertTrue(model instanceof FreecellModelMultiMove);
  }

  // Test create to throw an error when an invalid GameType is passed.
  @Test
  public void testCreateErrorInvalidArgument() {
    try {
      FreecellModelCreator.create(null);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals(FreecellModelCreator.ERROR_ILLEGAL_GAMETYPE, e.getMessage());
    }
  }
}
