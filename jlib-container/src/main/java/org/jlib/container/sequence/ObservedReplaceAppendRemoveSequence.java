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

/**
 * {@link ReplaceSequence} and {@link AppendSequence}.
 * 
 * @param <Item>
 *        type of items held in the {@link ObservedReplaceAppendRemoveSequence}
 * 
 * @author Igor Akkerman
 */
public interface ObservedReplaceAppendRemoveSequence<Item>
extends ReplaceAppendRemoveSequence<Item>, ObservedReplaceAppendSequence<Item>, ObservedRemoveSequence<Item> {

    /**
     * @return {@link ObservedReplaceRemoveSequenceTraverser} traversing the
     *         Items of this {@link ObservedReplaceAppendRemoveSequence}
     */
    @Override
    public ObservedReplaceRemoveSequenceTraverser<Item> createTraverser();
}