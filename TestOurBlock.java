package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull; // Make sure to import your new DiagonalBlock
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.CheckerBoard;
import edu.grinnell.csc207.blocks.DiagonalBlock;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Trimmed;
import edu.grinnell.csc207.blocks.VAlignment;

public class TestOurBlock {

    @Test
    public void testCheckerBoard() throws Exception {
        AsciiBlock block = new CheckerBoard('X', 'O', 4, 4);
        assertEquals("XOXO", block.row(0));
        assertEquals("OXOX", block.row(1));
        assertEquals("XOXO", block.row(2));
        assertEquals("OXOX", block.row(3));
        assertEquals(4, block.width());
        assertEquals(4, block.height());
    }

    @Test
    public void testPaddedBlock() throws Exception {
        AsciiBlock checkerBoard = new CheckerBoard('X', 'O', 2, 2);
        AsciiBlock paddedBlock = new Padded(checkerBoard, '.', HAlignment.CENTER, VAlignment.CENTER, 4, 4);
        
        assertEquals("....", paddedBlock.row(0));
        assertEquals(".XO.", paddedBlock.row(1));
        assertEquals(".OX.", paddedBlock.row(2));
        assertEquals("....", paddedBlock.row(3));
    }

    @Test
    public void testTrimmedBlock() throws Exception {
        AsciiBlock checkerBoard = new CheckerBoard('X', 'O', 5, 5);
        AsciiBlock trimmedBlock = new Trimmed(checkerBoard, HAlignment.CENTER, VAlignment.CENTER, 3, 3);
        
        assertEquals("OXO", trimmedBlock.row(0));
        assertEquals("XOX", trimmedBlock.row(1));
        assertEquals("OXO", trimmedBlock.row(2));
    }

    @Test
    public void testTrimmedBlockException() {
        AsciiBlock checkerBoard = new CheckerBoard('X', 'O', 5, 5);
        AsciiBlock trimmedBlock = new Trimmed(checkerBoard, HAlignment.CENTER, VAlignment.CENTER, 3, 3);
        
        // This should throw an exception since the row index is out of bounds
        Exception exception = assertThrows(Exception.class, () -> {
            trimmedBlock.row(5);
        });
        assertNotNull(exception); // Optionally check that an exception is thrown
    }

    @Test
    public void testEquality() {
        AsciiBlock block1 = new CheckerBoard('X', 'O', 4, 4);
        AsciiBlock block2 = new CheckerBoard('X', 'O', 4, 4);
        AsciiBlock block3 = new CheckerBoard('X', 'Y', 4, 4);
        
        assertTrue(block1.eqv(block2)); // Should be equal
        assertFalse(block1.eqv(block3)); // Should not be equal
    }

    @Test
    public void testDiagonalBlock() throws Exception {
        AsciiBlock block = new DiagonalBlock('*', ' ', 5, 5);
        assertEquals("*    ", block.row(0));
        assertEquals(" *   ", block.row(1));
        assertEquals("  *  ", block.row(2));
        assertEquals("   * ", block.row(3));
        assertEquals("    *", block.row(4));
        assertEquals(5, block.width());
        assertEquals(5, block.height());
    }

    @Test
    public void testDiagonalBlockEquality() {
        AsciiBlock block1 = new DiagonalBlock('*', ' ', 5, 5);
        AsciiBlock block2 = new DiagonalBlock('*', ' ', 5, 5);
        AsciiBlock block3 = new DiagonalBlock('#', ' ', 5, 5);
        
        assertTrue(block1.eqv(block2)); // Should be equal
        assertFalse(block1.eqv(block3)); // Should not be equal
    }
}