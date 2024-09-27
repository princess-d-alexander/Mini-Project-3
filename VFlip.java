package edu.grinnell.csc207.blocks;

/**
 * A vertically flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Princess Alexander
 */
public class VFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public VFlip(AsciiBlock original) {
    this.block = original;
  } // VFlip(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  @Override
  public String row(int i) throws Exception {
    // Get the row from the flipped index
    int flippedIndex = this.block.height() - 1 - i;
    return this.block.row(flippedIndex);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  @Override
  public int height() {
    return this.block.height();  // The height remains the same
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  @Override
  public int width() {
    return this.block.width();  // The width remains the same
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  @Override
  public boolean eqv(AsciiBlock other) {
    if (other instanceof VFlip) {
      VFlip that = (VFlip) other;
      return this.block.eqv(that.block);  // Compare the underlying blocks
    } // if
    return false; // return false if the block is not a VFlip
  } // eqv(AsciiBlock)
} // class VFlip
