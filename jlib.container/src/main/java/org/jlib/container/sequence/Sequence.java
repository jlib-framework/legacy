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

package org.jlib.container.sequence;

import java.util.List;

import org.jlib.container.Container;
import org.jlib.core.traverser.BidirectionalTraverser;
import org.jlib.core.traverser.BidirectionalTraversible;

/**
 * Ordered sequence of Items.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public interface Sequence<Item>
extends Container<Item>, BidirectionalTraversible<Item> {

    /**
     * Returns a {@link BidirectionalTraverser} traversing the Items of this
     * Sequence in proper sequence. The Item returned by the first call to
     * {@link BidirectionalTraverser#getNextItem()} is the first Item in the
     * Sequence.
     * 
     * @return BidirectionalTraverser over this Sequence
     */
    public SequenceTraverser<Item> createSequenceTraverser();

    /**
     * <p>
     * Verifies whether the specified Object is a Sequence containing equal
     * Items as this Sequence.
     * </p>
     * <p>
     * Two Items {@code item1} and {@code item2} are equal if and only if both
     * are {@code null} or both are equal by the {@link #equals(Object)} method.
     * </p>
     * 
     * @param otherObject
     *        Object to compare to this Sequence
     * @return {@code true} if {@code otherObject} is equal to this Sequence;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(final Object otherObject);

    /**
     * <p>
     * Returns a {@link List} containing all Items stored in this Sequence in
     * proper sequence.
     * </p>
     * <p>
     * The method provides the same functionality as the {@link #toCollection()}
     * method and has been introduced .
     * </p>
     * 
     * @return {@link List} containing the Items stored in this Sequence
     */
    public List<Item> toList();
}
