package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Princess Alexander
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock, AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBlock[])

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
   *   if i is outside the range of valid rows.
   */
  @Override
  public String row(int i) throws Exception {
    // Determine which block this row belongs to
    int rowIndex = i;
    for (AsciiBlock block : blocks) {
      if (rowIndex < block.height()) {
        // Determine padding for alignment
        int paddingLeft;
        int paddingRight;
        int diff = this.width() - block.width();

        switch (this.align) {
          case LEFT -> {
            paddingLeft = 0;
            paddingRight = diff;
          } //case
          case CENTER -> {
            paddingLeft = diff / 2;
            paddingRight = diff - paddingLeft;
          } //case
          case RIGHT -> {
            paddingLeft = diff;
            paddingRight = 0;
          } //case
          default -> throw new Exception("Unknown alignment");
        } // switch

        return " ".repeat(paddingLeft) + block.row(rowIndex) + " ".repeat(paddingRight);
        //return composed row
      } else {
        rowIndex -= block.height(); // move to the next block
      } // if/else
    } // for

    throw new Exception("Invalid row " + i); // if row is out of bounds
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  @Override
  public int height() {
    int totalHeight = 0;
    for (AsciiBlock block : this.blocks) {
      totalHeight += block.height();
    } // for
    return totalHeight; // return total height
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  @Override
  public int width() {
    int maxWidth = 0;
    for (AsciiBlock block : this.blocks) {
      if (block.width() > maxWidth) {
        maxWidth = block.width();
      } // if
    } // for
    return maxWidth; // return the width of the widest block
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
    if (other instanceof VComp) {
      VComp that = (VComp) other;
      return Arrays.equals(this.blocks, that.blocks) && this.align == that.align;
      // return comparison result
    } // if
    return false; // return false if not equivalent
  } // eqv(AsciiBlock)
} // class VComp

