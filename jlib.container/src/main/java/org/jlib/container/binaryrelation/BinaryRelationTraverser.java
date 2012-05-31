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

package org.jlib.container.binaryrelation;

import java.util.HashSet;

import org.jlib.container.NoSuchItemException;
import org.jlib.core.traverser.IterableTraverser;
import org.jlib.core.traverser.NoNextItemException;
import org.jlib.core.traverser.Traverser;

/**
 * Traverser over the Associations of a BinaryRelation.
 * 
 * @param <LeftValue>
 *        type of the objects on the left hand side of the binaryRelation
 * 
 * @param <RightValue>
 *        type of the objects on the right hand side of the binaryRelation
 * 
 * @author Igor Akkerman
 */
class BinaryRelationTraverser<LeftValue, RightValue>
implements Traverser<Association<LeftValue, RightValue>> {

    /** BinaryRelation traversed by this Traverser */
    private final BinaryRelation<LeftValue, RightValue> binaryRelation;

    /**
     * Traverser over the LeftValues of the BinaryRelation traversed by this
     * Traverser
     */
    private final Traverser<LeftValue> leftValuesTraverser;

    /**
     * Traverser over the RightValues of the BinaryRelation traversed by this
     * Traverser
     */
    private Traverser<RightValue> rightValuesTraverser;

    /** current LeftValue */
    private LeftValue leftValue;

    /**
     * Creates a new BinaryRelationTraverser.
     * 
     * @param binaryRelation
     *        BinaryRelation traversed by this Traverser
     */
    protected BinaryRelationTraverser(final BinaryRelation<LeftValue, RightValue> binaryRelation) {
        super();
        this.binaryRelation = binaryRelation;
        leftValuesTraverser = new IterableTraverser<>(binaryRelation.leftValues());
        if (leftValuesTraverser.isNextItemAccessible())
            getNextLeftValue();
        else
            rightValuesTraverser = new IterableTraverser<>(new HashSet<RightValue>());
    }

    /**
     * Retrieves the next LeftValue.
     */
    private void getNextLeftValue() {
        leftValue = leftValuesTraverser.getNextItem();
        rightValuesTraverser = new IterableTraverser<>(binaryRelation.rightSet(leftValue));
    }

    @Override
    public boolean isNextItemAccessible() {
        if (rightValuesTraverser.isNextItemAccessible())
            return true;

        if (!leftValuesTraverser.isNextItemAccessible())
            return false;

        getNextLeftValue();

        return true;
    }

    @Override
    public Association<LeftValue, RightValue> getNextItem()
    throws NoSuchItemException {
        if (!isNextItemAccessible())
            throw new NoNextItemException();

        return new Association<LeftValue, RightValue>(leftValue, rightValuesTraverser.getNextItem());
    }
}