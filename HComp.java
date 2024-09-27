package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Princess Alexander
 */
public class HComp implements AsciiBlock {
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
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock, AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBlock[])

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
    StringBuilder result = new StringBuilder();

    // For each block in the composition
    for (AsciiBlock block : this.blocks) {
      // Calculate the height difference between the tallest block and this block
      int heightDiff = this.height() - block.height();

      // Determine the row of the current block to print based on the alignment
      int blockRow = switch (this.align) {
        case TOP -> i;
        case CENTER -> i - (heightDiff / 2);
        case BOTTOM -> i - heightDiff;
      }; // switch

      // Append the block's row if valid, otherwise add spaces
      if (blockRow >= 0 && blockRow < block.height()) {
        result.append(block.row(blockRow));
      } else {
        result.append(" ".repeat(block.width())); // Add spaces if the block doesn't have this row
      } // if/else
    } // for

    return result.toString(); // return the composed row
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  @Override
  public int height() {
    // The height of the composition is the height of the tallest block
    int maxHeight = 0;
    for (AsciiBlock block : this.blocks) {
      if (block.height() > maxHeight) {
        maxHeight = block.height();
      } // if
    } // for
    return maxHeight; // return
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  @Override
  public int width() {
    // The width of the composition is the sum of the widths of all blocks
    int totalWidth = 0;
    for (AsciiBlock block : this.blocks) {
      totalWidth += block.width();
    } // for
    return totalWidth; // return
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
    if (other instanceof HComp) {
      HComp that = (HComp) other;
      return Arrays.equals(this.blocks, that.blocks) && this.align == that.align;
      // return comparison result
    } // if
    return false; // return false if not equivalent
  } // eqv(AsciiBlock)

} // class HComp
