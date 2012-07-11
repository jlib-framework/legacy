/*
 * jlib - The Free Java Library
 * 
 * http://www.jlib.org
 * 
 * Copyright (c) 2006-2008 Igor Akkerman
 * 
 * jlib is distributed under the
 * 
 * COMMON PUBLIC LICENSE VERSION 1.0
 * 
 * http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.container.sequence.index;

import org.jlib.container.sequence.IllegalSequenceArgumentException;
import org.jlib.container.sequence.IllegalSequenceStateException;
import org.jlib.container.sequence.ReplaceSequence;
import org.jlib.container.sequence.Sequence;
import org.jlib.core.traverser.ReplaceTraverser;

/**
 * {@link IndexSequence} and {@link ReplaceSequence}.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public interface ReplaceIndexSequence<Item>
extends IndexSequence<Item>, ReplaceSequence<Item> {

    /**
     * Replaces the Item at the specified index in this
     * {@link ReplaceIndexSequence} by the specified Items.
     * 
     * @param index
     *        integer specifying the index
     * 
     * @param newItem
     *        new Item to store
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if {@code index < getFirstIndex() || index > getLastIndex()}
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of {@code newItem} prevents the operation from
     *         being performed
     * 
     * @throws IllegalSequenceStateException
     *         if an error occurs performing the operation
     */
    public void replace(final int index, final Item newItem)
    throws SequenceIndexOutOfBoundsException, IllegalSequenceArgumentException, IllegalSequenceStateException;

    /**
     * @return {@link ReplaceIndexSequence} view of the specified subsequence
     */
    @Override
    public ReplaceIndexSequence<Item> getSubsequenceView(final int fromIndex, final int toIndex)
    throws SequenceIndexOutOfBoundsException, InvalidSequenceIndexRangeException;

    /**
     * Returns an {@link IndexSequenceTraverser} and {@link ReplaceTraverser}
     * traversing the Items of this {@link ReplaceIndexSequence} in proper
     * order. That is, the Item returned by the first call to
     * {@link IndexSequenceTraverser#getNextItem()} is the Item stored at the
     * first index.
     * 
     * @return {@link ReplaceIndexSequenceTraverser} over this
     *         {@link ReplaceIndexSequence}
     */
    public ReplaceIndexSequenceTraverser<Item> createTraverser();

    /**
     * Returns an {@link IndexSequenceTraverser} and {@link ReplaceTraverser}
     * traversing the Items of this {@link ReplaceIndexSequence} in proper
     * order. That is, the Item returned by the first call to
     * {@link IndexSequenceTraverser#getNextItem()} is the Item stored at the
     * specified start index.
     * 
     * @param startIndex
     *        integer specifying the index of the first Item to traverse
     * 
     * @return {@link ReplaceIndexSequenceTraverser} over this
     *         {@link ReplaceIndexSequence}
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if
     *         {@code startIndex < getFirstIndex() || startIndex > getLastIndex()}
     */
    public ReplaceIndexSequenceTraverser<Item> createTraverser(final int startIndex)
    throws SequenceIndexOutOfBoundsException;
}
