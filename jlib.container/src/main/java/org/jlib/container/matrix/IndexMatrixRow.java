/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * Copyright (c) 2006-2008 Igor Akkerman
 *
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.container.matrix;

/**
 * Row of an {@link IndexMatrix}.
 * 
 * @param <Entry>
 *        type of the elements in the IndexMatrix
 * 
 * @author Igor Akkerman
 */
class IndexMatrixRow<Entry>
extends IndexMatrixEntity<Entry> {

    /**
     * Creates a new {@link IndexMatrixRow} representation of the specified row
     * of the specified {@link IndexMatrix}.
     * 
     * @param matrix
     *        {@link IndexMatrix} owning this {@link IndexMatrixRow}
     * 
     * @param rowIndex
     *        integer specifying the index of this {@link IndexMatrixRow}
     */
    protected IndexMatrixRow(final IndexMatrix<Entry> matrix, final int rowIndex) {
        super(matrix, rowIndex);
    }

    /**
     * Creates a new {@link IndexMatrixColumn} representation of the specified
     * part of the specified row of the specified {@link IndexMatrix}.
     * 
     * @param matrix
     *        {@link IndexMatrix} owning this {@link IndexMatrixRow}
     * 
     * @param rowIndex
     *        integer specifying the index of this {@link IndexMatrixRow}
     * 
     * @param firstColumnIndex
     *        integer specifying the first row index of the column part
     * 
     * @param lastColumnIndex
     *        integer specifying the last row index of the column part
     */
    protected IndexMatrixRow(final IndexMatrix<Entry> matrix, final int rowIndex, final int firstColumnIndex,
                             final int lastColumnIndex) {
        super(matrix, rowIndex, firstColumnIndex, lastColumnIndex);
    }

    /**
     * Returns the Entry stored at the specified column index in this
     * {@link IndexMatrixRow}.
     * 
     * @param columnIndex
     *        integer specifying the column index
     * 
     * @return Entry stored at {@code columnIndex}
     */
    @Override
    protected Entry getStoredElement(final int columnIndex) {
        return getMatrixEntry(columnIndex, getEntityIndex());
    }
}
