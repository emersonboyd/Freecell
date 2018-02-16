package cs3500.hw03;

import java.io.IOException;
import java.util.List;

import cs3500.hw02.FreecellOperations;

/**
 * This is the interface of the Freecell controller. It is parameterized over the card type, i.e.
 * when you implement it, you can substitute K with your implementation of a card.
 */
public interface IFreecellController<K> {

  /**
   * Plays a new game of Freecell using the provided model, number of cascade and open piles and the
   * provided deck. If “shuffle” is set to false, the deck must be used as-is, else the deck should
   * be shuffled.
   *
   * @param deck        the deck to be dealt
   * @param model       the Freecell model to refer to
   * @param numCascades number of cascade piles
   * @param numOpens    number of open piles
   * @param shuffle     if true, shuffleDeck the deck else deal the deck as-is
   * @throws IllegalArgumentException if the model or deck are null
   * @throws IOException              only if the controller is unable to successfully receive
   *                                  input or transmit output
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
                int numOpens, boolean shuffle) throws IllegalArgumentException, IOException;
}
