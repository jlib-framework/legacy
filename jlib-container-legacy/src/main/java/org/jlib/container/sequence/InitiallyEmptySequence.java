/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2013 Igor Akkerman
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jlib.container.operation.sequence;

import java.util.Collection;

import org.jlib.operator.observer.ValueObserver;
import org.jlib.operator.observer.ValueObserverException;
import org.jlib.container.iterator.iterator.ObservedReplaceIterable;
import Iterable;

import org.jlib.container.operation.InvalidContainerArgumentException;
import org.jlib.container.operation.InvalidContainerStateException;
import org.jlib.container.operation.ItemToRemoveNotContainedException;
import org.jlib.container.operation.ObservedRemoveAll;
import org.jlib.container.operation.ObservedRemoveMultiple;
import org.jlib.container.operation.legacy.AbstractEmpty;

/**
 * {@link Sequence} that is empty when created.
 *
 * @param <Item>
 *        type of the items
 *
 * @author Igor Akkerman
 */
public abstract class InitiallyEmptySequence<Item>
extends AbstractEmpty<Item>
implements Sequence<Item>,
           ObservedReplaceIterable<Item>,
           ObservedRemoveMultiple<Item>,
           ObservedRemoveAll<Item> {

    /**
     * Creates a new {@link InitiallyEmptySequence}.
     */
    protected InitiallyEmptySequence() {
        super();
    }

    public void remove(final Item item)
    throws ItemToRemoveNotContainedException {
        // throw new ItemToRemoveNotContainedException(this, item);
    }

    public void removeAll()
    throws ItemToRemoveNotContainedException {
        // intentionally blank
    }

    public void remove(final IterableContainer<? extends Item> items)
    throws ItemToRemoveNotContainedException {
        // intentionally blank
    }

    public void remove(final Collection<? extends Item> items)
    throws ItemToRemoveNotContainedException, InvalidContainerStateException {
        // intentionally blank
    }

    public void remove(final Iterable<? extends Item> items)
    throws ItemToRemoveNotContainedException, InvalidContainerStateException {
        // intentionally blank
    }

    @SuppressWarnings("unchecked")
    public void remove(final Item... items)
    throws ItemToRemoveNotContainedException, InvalidContainerStateException {
        // intentionally blank
    }

    public void retain(final IterableContainer<? extends Item> items)
    throws ItemToRemoveNotContainedException, InvalidContainerStateException {
        // intentionally blank
    }

    public void retain(final Iterable<? extends Item> items)
    throws InvalidContainerArgumentException, InvalidContainerStateException {
        // intentionally blank
    }

    public void retain(final Collection<? extends Item> items)
    throws ItemToRemoveNotContainedException, InvalidContainerStateException {
        // intentionally blank
    }

    @SuppressWarnings("unchecked")
    public void retain(final Item... items)
    throws ItemToRemoveNotContainedException, InvalidContainerStateException {
        // intentionally blank
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final Item item, final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException {
        // intentionally blank
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final IterableContainer<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException {
        // intentionally blank
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final Collection<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException {
        // intentionally blank
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final ValueObserver<Item>[] observers, final Item... items)
    throws InvalidContainerArgumentException {
        // intentionally blank
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeAll(final ValueObserver<Item>... observers)
    throws InvalidContainerStateException {
        // intentionally blank
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final Iterable<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException, InvalidContainerStateException, ValueObserverException {
        // intentionally blank
    }

    @SuppressWarnings("unchecked")
    public void retain(final IterableContainer<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException, InvalidContainerStateException, ValueObserverException {
        // intentionally blank
    }

    @SuppressWarnings("unchecked")
    public void retain(final Collection<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException, InvalidContainerStateException, ValueObserverException {
        // intentionally blank
    }

    @SuppressWarnings("unchecked")
    public void retain(final ValueObserver<Item>[] observers, final Item... items)
    throws InvalidContainerArgumentException, InvalidContainerStateException, ValueObserverException {
        // intentionally blank
    }

    @Override
    public EmptySequenceIterator<Item> iterator() {
        return EmptySequenceIterator.getInstance();
    }

    @Override
    public boolean hasMatchingProperties(final IterableContainer<Item> otherContainer) {
        return false;
    }

    // equals/hashCode don't need to be extended as Object.equals already checks for identity
}