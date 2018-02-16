package cs3500.hw03.mocks;

import cs3500.hw02.FreecellModel;

/**
 * Represents a direct clone of the FreecellModel except for the fact that isGameOver() always
 * returns true.
 */
public class FreecellModelMock extends FreecellModel {
  @Override
  public boolean isGameOver() {
    return true;
  }
}
