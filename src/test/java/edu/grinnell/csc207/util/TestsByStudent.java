package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;

import org.junit.jupiter.api.Test;

/**
 * Some tests that I came up with for the Matrix class.
 *
 * @author Sarah Deschamps
 */
public class TestsByStudent {
  /**
   * Make sure we can create 0x0 matrices that return 0 in width and height.
   */
  @Test
  public void initializeEmpty() {
    Matrix<String> m = new MatrixV0<String>(0, 0);
    assertEquals(0, m.width());
    assertEquals(0, m.height());
  } // initializeEmpty()

  /**
   * Make sure we can add rows and columns to 0x0 matrices.
   */
  @Test
  public void inserts() {
    Matrix<String> m = new MatrixV0<String>(0, 0, ":)");
    m.insertCol(0);
    m.insertRow(0);
    assertMatrixEquals(new String[][] {{":)"}}, m, "Can add elements to an empty matrix");
  } // inserts()

  /**
   * Make sure get works properly after adding rows and columns.
   */
  @Test
  public void testGet() {
    Matrix<String> m = new MatrixV0<String>(2, 2, ":)");
    try {
      m.insertRow(1, new String[] {"a", "b"});
    } catch (ArraySizeException e) {
      fail("The width or height was not initialized properly");
    } // try-catch
    m.insertCol(2);
    assertEquals("b", m.get(1, 1), "Get works properly after adding rows");
  } // testGet()

  /**
   * Make sure set works properly.
   */
  @Test
  public void testSet() {
    Matrix<String> m = new MatrixV0<String>(2, 2, ":)");
    m.set(1, 1, ":D");
    assertEquals(":D", m.get(1, 1), "Matrix can set properly");
  } // testSet()

  /*
   * Test height.
   */
  @Test
  public void testHeight() {
    Matrix<String> m = new MatrixV0<String>(2, 2, ":)");
    m.insertRow(2);
    assertEquals(3, m.height(), "Matrix can get height");
  } // testHeight()

  /**
   * Test width.
   */
  @Test
  public void testWidth() {
    Matrix<String> m = new MatrixV0<String>(2, 2, ":)");
    m.insertCol(2);
    assertEquals(3, m.width(), "Matrix returns correct width");
  } // testWidth()

  /**
   * Test that inserting rows works as expected.
   */
  @Test
  public void testInsertRow() {
    Matrix<String> m = new MatrixV0<String>(2, 2, ":)");
    try {
      m.insertRow(2, new String[] {"a", "b"});
    } catch (ArraySizeException e) {
      fail("Matrix size not properly initialized");
    } // try-catch
    assertMatrixEquals(new String[][] {{":)", ":)"}, {":)", ":)"}, {"a", "b"}},
                       m,
                       "Add row works");
  } // testInsertRow()

  /**
   * Test that inserting columns works as expected.
   */
  @Test
  public void testInsertCols() {
    Matrix<String> m = new MatrixV0<String>(2, 2, ":)");
    try {
      m.insertCol(0, new String[] {"a", "b"});
    } catch (ArraySizeException e) {
      fail("Matrix size not properly initialized");
    } // try-catch
    assertMatrixEquals(new String[][] {{"a", ":)", ":)"}, {"b", ":)", ":)"}},
                       m,
                       "Adds columns properly");
  } // testInsertCols()

  /**
   * Test fill region.
   */
  @Test
  public void testFillRegion() {
    Matrix<String> m = new MatrixV0<String>(3, 3, ":)");
    m.fillRegion(0, 0, 2, 2, "x");
    assertMatrixEquals(new String[][] {{"x","x",":)"}, {"x","x",":)"}, {":)",":)",":)"}},
                       m,
                       "Fills region properly");
  } // testFillRegion()

  /**
   * Fills a line properly.
   */
  @Test
  public void testFillLine() {
    Matrix<String> m = new MatrixV0<String>(3, 3, ":)");
    m.fillLine(0, 0, 1, 1, 3, 3, "x");
    assertMatrixEquals(new String[][] {{"x", ":)", ":)"}, {":)", "x", ":)"}, {":)", ":)", "x"}},
                       m,
                       "Fills line properly");
  } // testFillLine()

  /**
   * Can clone matrices.
   */
  @Test
  public void testClone() {
    Matrix<String> m = new MatrixV0<String>(3, 3, ":)");
    Matrix n = m.clone();
    assertEquals(m, n, "Clone works properly");
  } // testClone()

  /**
   * Equals works properly.
   */
  @Test
  public void testMatrixEquals() {
    Matrix<String> m = new MatrixV0<String>(3, 3, ":)");
    Matrix n = m.clone();
    assertEquals(true, m.equals(n), "Equals works properly");
  } // testMatrixEquals()
} // class TestsByStudent
