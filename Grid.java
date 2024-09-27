package edu.grinnell.csc207.blocks;

/**
 * A grid of a single text block.
 *
 * @author Samuel A. Rebelsky
 * @author Princess Alexander
 */
public class Grid implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * One element of the grid.
   */
  AsciiBlock element; // The block to repeat in the grid

  /**
   * The number of times the element is repeated horizontally.
   */
  int hreps; // Horizontal repetitions

  /**
   * The number of times the element is repeated vertically.
   */
  int vreps; // Vertical repetitions

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new grid with the specified arrangement.
   *
   * @param gridElement
   *   The element in the grid.
   * @param horizRepetitions
   *   The number of horizontal repetitions in the grid.
   * @param vertRepetitions
   *   The number of vertical repetitions in the grid.
   */
  public Grid(AsciiBlock gridElement, int horizRepetitions, int vertRepetitions) {
    this.element = gridElement;
    this.hreps = horizRepetitions;
    this.vreps = vertRepetitions;
  } // Grid(AsciiBlock, int, int)

  // +--------------------+------------------------------------------
  // | AsciiBlock methods |
  // +--------------------+

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
    if (i < 0 || i >= this.height()) {
      throw new Exception("Invalid row " + i); // Throw exception for invalid row
    } // if

    // Determine which row of the original block this corresponds to
    int blockRow = i % this.element.height();

    // Get the block's row and repeat it horizontally
    String repeatedRow = this.element.row(blockRow).repeat(this.hreps);

    return repeatedRow; // return
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  @Override
  public int height() {
    return this.element.height() * this.vreps; // Return total height based on vertical repetitions
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  @Override
  public int width() {
    return this.element.width() * this.hreps; // Return total width based on horizontal repetitions
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
    if (other instanceof Grid) {
      Grid that = (Grid) other;
      return this.hreps == that.hreps && this.vreps == that.vreps && this.element.eqv(that.element);
      // return comparison result
    } // if
    return false; // return false if not equivalent
  } // eqv(AsciiBlock)

} // class Grid
