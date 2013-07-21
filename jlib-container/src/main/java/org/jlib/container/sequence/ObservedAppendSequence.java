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

package org.jlib.container.sequence;

import java.util.Collection;

import org.jlib.container.Container;
import org.jlib.core.observer.ValueObserver;

/**
 * {@link AppendSequence} to which Items can be appended.
 *
 * @param <Item>
 *        type of items held in the {@link Sequence}
 *
 * @author Igor Akkerman
 */
public interface ObservedAppendSequence<Item>
extends AppendSequence<Item> {

    /**
     * Appends the specified Item to this {@link ObservedAppendSequence}.
     *
     * @param item
     *        Item to append
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     *
     * @throws InvalidSequenceArgumentException
     *         if some property of {@code item} prevents it from being appended,
     *         for instance, if it is already contained
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SuppressWarnings("unchecked")
    public void append(final Item item, final ValueObserver<Item>... observers)
    throws InvalidSequenceArgumentException;

    /**
     * Appends all Items contained by the specified {@link Container} to this
     * {@link ObservedAppendSequence}.
     *
     * @param items
     *        {@link Container} containing the Items to append
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     *
     * @throws InvalidSequenceArgumentException
     *         if {@code items}
     *
     * @throws InvalidContainerArgumentException
     *         if some property of an Item in {@code items} prevents it from
     *         being appended, for instance, if it is already contained
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SuppressWarnings("unchecked")
    public void append(final Container<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidSequenceArgumentException;

    /**
     * Appends all Items contained by the specified {@link Collection} to this
     * {@link ObservedAppendSequence}.
     *
     * @param items
     *        {@link Collection} containing the Items to append
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     *
     * @throws InvalidSequenceArgumentException
     *         if some property of an Item in {@code items} prevents it from
     *         being appended, for instance, if it is already contained
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SuppressWarnings("unchecked")
    public void append(final Collection<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidSequenceArgumentException;

    /**
     * Appends all specified Items to this {@link ObservedAppendSequence}.
     *
     * @param observers
     *        array of {@link ValueObserver} instances attending the operation
     *
     * @param items
     *        comma separated sequence of Items to append
     *
     * @throws InvalidSequenceArgumentException
     *         if some property of an Item in {@code items} prevents it from
     *         being appended, for instance, if it is already contained
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SuppressWarnings("unchecked")
    public void append(ValueObserver<Item>[] observers, final Item... items)
    throws InvalidSequenceArgumentException;
}
