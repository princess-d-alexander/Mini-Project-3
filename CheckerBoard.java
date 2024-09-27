package edu.grinnell.csc207.blocks;

/**
 * A checkerboard ASCII block.
 * 
 * This class generates a checkerboard pattern using two specified characters.
 * 
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class CheckerBoard implements AsciiBlock {
    private char char1; // First character for the checkerboard
    private char char2; // Second character for the checkerboard
    private int width;  // Width of the checkerboard
    private int height; // Height of the checkerboard

    /**
     * Constructor for creating a checkerboard block.
     *
     * @param char1 The first character.
     * @param char2 The second character.
     * @param width The width of the checkerboard.
     * @param height The height of the checkerboard.
     */
    public CheckerBoard(char char1, char char2, int width, int height) {
        this.char1 = char1;
        this.char2 = char2;
        this.width = width;
        this.height = height;
    }

    @Override
    public String row(int i) {
        StringBuilder row = new StringBuilder();
        for (int j = 0; j < width; j++) {
            // Alternate characters based on the sum of indices
            if ((i + j) % 2 == 0) {
                row.append(char1);
            } else {
                row.append(char2);
            }
        }
        return row.toString();
    }

    @Override
    public int height() {
        return height; // Return the height of the checkerboard
    }

    @Override
    public int width() {
        return width; // Return the width of the checkerboard
    }

    @Override
    public boolean eqv(AsciiBlock other) {
        if (!(other instanceof CheckerBoard)) {
            return false;
        }
        CheckerBoard o = (CheckerBoard) other;
        return this.char1 == o.char1 && this.char2 == o.char2 &&
               this.width == o.width && this.height == o.height;
    }
}
