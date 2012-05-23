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

package org.jlib.container.sequence.index;

import org.jlib.container.sequence.AbstractSequenceStateIterator;
import org.jlib.container.sequence.BeginningOfSequenceIteratorState;
import org.jlib.container.sequence.EndOfSequenceIteratorState;
import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.SequenceIteratorState;

/**
 * {@link IndexSequenceIterator} traversing the elements in the proper order.
 * 
 * @param <Element>
 *        type of elements held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public class IndexSequenceStateIterator<Element>
extends AbstractSequenceStateIterator<Element>
implements IndexSequenceIterator<Element> {

    /**
     * Initialized of
     * 
     * 
     * @author Igor Akkerman
     */
    private static class Initializer<Element> {

        private final IndexSequence<Element> sequence;

        private final SequenceIteratorState<Element> beginningOfSequenceState;

        private final MiddleOfIndexSequenceIteratorState<Element> middleOfSequenceState;

        private final SequenceIteratorState<Element> endOfSequenceState;

        private Initializer(final IndexSequence<Element> sequence, final int startIndex) {

            this.sequence = sequence;

            beginningOfSequenceState = new BeginningOfSequenceIteratorState<Element>() {

                @Override
                public Element next() {
                    return sequence.getFirst();
                }

                @Override
                public SequenceIteratorState<Element> getNextState() {
                    return middleOfSequenceState;
                }
            };

            endOfSequenceState = new EndOfSequenceIteratorState<Element>() {

                @Override
                public Element previous()
                throws NoSuchSequenceElementException {
                    return sequence.getLast();
                }

                @Override
                public SequenceIteratorState<Element> getPreviousState() {
                    middleOfSequenceState.setNextElementIndex(sequence.getLastIndex() - 1);

                    return middleOfSequenceState;
                }
            };

            middleOfSequenceState = new MiddleOfIndexSequenceIteratorState<Element>(sequence, startIndex) {

                @Override
                protected SequenceIteratorState<Element> getReturnedElementState() {
                    return Initializer.this.getCurrentState(getNextElementIndex());
                }

            };
        }

        /**
         * Returns the new {@link SequenceIteratorState} after an Element has
         * been returned and the specified index of the next Element has been
         * set.
         * 
         * @param nextElementIndex
         *        integer specifying the index of the next Element;
         *        {@code sequence.getLastIndex + 1} represents the end of the
         *        {@link IndexSequence}
         * 
         * @return new {@link SequenceIteratorState}
         */
        private SequenceIteratorState<Element> getCurrentState(final int nextElementIndex) {
            if (nextElementIndex == sequence.getFirstIndex())
                return beginningOfSequenceState;

            if (nextElementIndex == sequence.getLastIndex() + 1)
                return endOfSequenceState;

            return middleOfSequenceState;
        }
    }

    /**
     * Creates a new {@link IndexSequenceStateIterator} over the Elements of the
     * specified {@link IndexSequence} beginning at its first index.
     * 
     * @param sequence
     *        IndexSequence to traverse
     */
    protected IndexSequenceStateIterator(final IndexSequence<Element> sequence) {
        this(sequence, sequence.getFirstIndex());
    }

    /**
     * Creates a new DefaultReplaceIndexSequenceIterator over the Elements of
     * the specified IndexSequence starting the traversal at the specified
     * index.
     * 
     * @param sequence
     *        ReplaceIndexSequence to traverse
     * 
     * @param startIndex
     *        integer specifying the start index of the traversal
     * 
     * @throws SequenceIndexOutOfBoundsException
     *         if
     *         {@code startIndex < sequence.getFirstIndex() || startIndex > sequence.getLastIndex()}
     */
    protected IndexSequenceStateIterator(final IndexSequence<Element> sequence, final int startIndex)
    throws SequenceIndexOutOfBoundsException {
        super(new Initializer(sequence, startIndex).getCurrentState(startIndex));
    }
}
