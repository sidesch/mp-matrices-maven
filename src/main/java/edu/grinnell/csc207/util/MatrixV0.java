package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Sarah
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The matrix containing the data.
   */
  private T[][] contents;

  /**
   * The default value to fill in the array.
   */
  private T deflt;

  /**
   * The width of the array.
   */
  private int wid;

  /**
   * The height of the array.
   */
  private int ht;

  /**
   * The width capacity of the array.
   */
  private int widCap;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int width, int height, T def) {
    this.contents = (T[][]) new Object[height][];
    for (int i = 0; i < height; i++) {
      this.contents[i] = (T[]) new Object[width];
      for (int j = 0; j < width; j++) {
        this.contents[i][j] = def;
      } // for
    } // for
    this.deflt = def;
    this.wid = width;
    this.ht = height;
    this.widCap = width;
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row >= this.ht || col >= this.wid) {
      throw new IndexOutOfBoundsException();
    } // if
    return this.contents[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row > this.ht || col > this.wid) {
      throw new IndexOutOfBoundsException();
    } // if
    this.contents[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.ht;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.wid;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row) {
    T[] newRow = (T[]) new Object[this.wid];
    for (int i = 0; i < this.wid; i++) {
      newRow[i] = this.deflt;
    } // for
    this.insertRowHelper(row, newRow);
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (vals.length != this.wid) {
      throw new ArraySizeException();
    } // if
    this.insertRowHelper(row, vals);
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col) {
    T[] newCol = (T[]) new Object[this.ht];
    for (int i = 0; i < this.ht; i++) {
      newCol[i] = this.deflt;
    } // for
    this.insertColHelper(col, newCol);
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (vals.length != this.ht) {
      throw new ArraySizeException();
    } // if
    this.insertColHelper(col, vals);
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row > this.ht) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = row; i < this.ht - 1; i++) {
      this.contents[i] = this.contents[i + 1];
    } // for
    this.ht--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col > this.wid) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = 0; i < this.ht - 1; i++) {
      for (int j = col; j < this.wid - 1; j++) {
        this.contents[i][j] = this.contents[i][j + 1];
      } // for
    } // for
    this.wid--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throws IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (startRow < 0 || startRow > this.ht
        || startCol < 0 || startCol > this.wid
        || endRow < 0 || endRow > this.ht
        || endCol < 0 || endRow > this.wid) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int r = startRow; r < endRow; r++) {
      for (int c = startCol; c < endCol; c++) {
        this.contents[r][c] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throws IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (startRow < 0 || startRow >= this.ht
        || startCol < 0 || startCol >= this.wid) {
      throw new IndexOutOfBoundsException();
    } // if
    while (startRow < endRow && startCol < endCol
           && startRow < this.ht && startCol < this.wid) {
      this.contents[startRow][startCol] = val;
      startRow += deltaRow;
      startCol += deltaCol;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    MatrixV0<T> duplicate = new MatrixV0<T>(this.ht, this.wid);
    for (int i = 0; i < this.ht; i++) {
      for (int j = 0; j < this.wid; j++) {
        duplicate.set(i, j, this.contents[i][j]);
      } // for
    } // for
    return duplicate;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    if (other instanceof MatrixV0) {
      if (this.wid == ((MatrixV0<T>) other).width() && this.ht == ((MatrixV0<T>) other).height()) {
        for (int i = 0; i < this.ht; i++) {
          for (int j = 0; j < this.wid; j++) {
            if (this.contents[i][j] != ((MatrixV0<T>) other).get(i, j)) {
              return false;
            } // if
          } // for
        } // for
        return true;
      } // if
    } // if
    return false;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()

  /**
   * Inserts a row into the matrix, assuming values array satisfies preconditions.
   * @param row the index to insert the row into.
   * @param values the values to be inserted into the row.
   * @throws IndexOutOfBoundsException if the indeces are out of bounds.
   */
  private void insertRowHelper(int row, T[] values) {
    if (row < 0 || row > this.ht) {
      throw new IndexOutOfBoundsException();
    } // if
    if (this.ht + 1 >= this.contents.length) {
      this.contents = java.util.Arrays.copyOf(this.contents, this.contents.length * 2);
    } // if
    for (int r = this.ht - 1; r >= row; r--) {
      this.contents[r + 1] = java.util.Arrays.copyOf(this.contents[r], this.contents[r].length);
    } // for
    for (int c = 0; c < this.wid; c++) {
      this.contents[row][c] = values[c];
    } // for
    this.ht++;
  } // insertRowHelper(int, T[])

  /**
   * Inserts a column into the matrix, assuming values array satisfies preconditions.
   * @param col the index to insert the column into.
   * @param values the values to be inserted into the column.
   * @throws IndexOutOfBoundsException if the indeces are out of bounds.
   */
  private void insertColHelper(int col, T[] values) {
    if (col < 0 || col > this.wid) {
      throw new IndexOutOfBoundsException();
    } // if
    if (this.wid + 1 >= this.widCap) {
      for (int i = 0; i < this.contents.length; i++) {
        this.contents[i] = java.util.Arrays.copyOf(this.contents[i], this.contents[i].length * 2);
      } // for
      this.widCap *= 2;
    } // if
    for (int i = 0; i < this.ht; i++) {
      for (int j = this.wid - 1; j >= col; j--) {
        this.contents[i][j + 1] = this.contents[i][j];
      } // for
    } // for
    for (int i = 0; i < this.ht; i++) {
      this.contents[i][col] = values[i];
    } // for
    this.wid++;
  } // insertColHelper(int, T[])
} // class MatrixV0
