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

package org.jlib.container;

import org.jlib.core.observer.ValueObserver;
import org.jlib.core.observer.ValueObserverException;
import org.jlib.core.traverser.InvalidTraversableArgumentException;
import org.jlib.core.traverser.InvalidTraversableStateException;

/**
 * Ability to removeItem Items; the removeItem operations can be attended by {@link ValueObserver}
 * instances.
 *
 * @param <Item>
 *        type of items held in the {@link TraversableContainer}
 *
 * @author Igor Akkerman
 */
public interface ObservedRetainItemsByArray<Item>
extends ItemOperationStrategy<Item> {

    /**
     * Removes all Items from this {@link ObservedRemoveItemByItem}
     * <em>except</em> the specified Items.
     *
     * @param items
     *        comma separated sequence of Items to retainItems
     *
     * @param observers
     *        array of {@link ValueObserver} instances attending the removal
     *
     * @throws InvalidTraversableArgumentException
     *         if the operation cannot be completed due to some property of one
     *         Item in {@code items}
     *
     * @throws InvalidTraversableStateException
     *         if an error occurs during the operation
     *
     * @throws ValueObserverException
     *         if an error occurs during the {@link ValueObserver} operation
     */
    @SuppressWarnings("unchecked")
    public void retainItems(Item[] items, ValueObserver<Item>... observers)
    throws InvalidTraversableArgumentException, InvalidTraversableStateException, ValueObserverException;

    @SuppressWarnings("unchecked")
    public void retainItems(ValueObserver<Item>[] observers, Item... items)
    throws InvalidTraversableArgumentException, InvalidTraversableStateException, ValueObserverException;
}
