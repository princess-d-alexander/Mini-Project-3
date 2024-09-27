package edu.grinnell.csc207.blocks;

/**
 * A block that surrounds another block with a specified character.
 * @author Samuel A. Rebelsky
 * @author Princess Alexander
 */
public class Surrounded implements AsciiBlock {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The block we are surrounding.
   */
  AsciiBlock innerBlock; // The block to surround

  /**
   * The character we use for surrounding the block.
   */
  char surroundChar; // The character used for the border

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new Surrounded block.
   *
   * @param block The inner block to be surrounded.
   * @param ch The character used for the surrounding border.
   */
  public Surrounded(AsciiBlock block, char ch) {
    this.innerBlock = block;
    this.surroundChar = ch;
  } // Surrounded(AsciiBlock, char)

  // +--------------------+------------------------------------------
  // | AsciiBlock methods |
  // +--------------------+

  /**
   * Get the row at the given index.
   *
   * @param i The row number.
   *
   * @return The specified row, including the surrounding characters.
   * @exception Exception if the row number is invalid.
   */
  @Override
  public String row(int i) throws Exception {
    if (i == 0 || i == this.height() - 1) {
      // Top or bottom border
      return String.valueOf(surroundChar).repeat(this.width()); // return
    } else if (i > 0 && i < this.height() - 1) {
      // Rows from the inner block
      return surroundChar + innerBlock.row(i - 1) + surroundChar; // return
    } else {
      throw new Exception("Invalid row " + i); // throw exception for invalid row
    } // if/else
  } // row(int)

  /**
   * Determine the height of the block.
   *
   * @return The height of the surrounded block.
   */
  @Override
  public int height() {
    return innerBlock.height() + 2; // return the inner height plus top and bottom border
  } // height()

  /**
   * Determine the width of the block.
   *
   * @return The width of the surrounded block.
   */
  @Override
  public int width() {
    return innerBlock.width() + 2; // return the inner width plus left and right border
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and false otherwise.
   */
  @Override
  public boolean eqv(AsciiBlock other) {
    if (other instanceof Surrounded) {
      Surrounded that = (Surrounded) other;
      return this.surroundChar == that.surroundChar && this.innerBlock.eqv(that.innerBlock);
      // return comparison result
    } // if
    return false; // return false if not equivalent
  } // eqv(AsciiBlock)

} // class Surrounded
