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

package org.jlib.core.storage;

import static org.jlib.core.math.MathUtility.count;

import org.jlib.core.system.AbstractCloneable;

/**
 * Skeletal implementation of a {@link LinearIndexStorage}.
 *
 * @param <Item>
 *        type of the {@link Item}s stored in the {@link AbstractLinearIndexStorage}
 *
 * @author Igor Akkerman
 */
public abstract class AbstractLinearIndexStorage<Item>
extends AbstractCloneable
implements LinearIndexStorage<Item> {

    /** array index of the first {@link Item} */
    private Integer firstItemIndex;

    /** array index of the last {@link Item} */
    private Integer lastItemIndex;

    /**
     * Creates a new {@link AbstractLinearIndexStorage}.
     */
    protected AbstractLinearIndexStorage() {
        super();
    }

    @Override
    public final void initialize(final int capacity, final int firstItemIndex, final int lastItemIndex,
                                 final ItemsCopyDescriptor... copyDescriptors)
    throws LinearIndexStorageException {
        ensureInitializationArgumentsValid(capacity, firstItemIndex, lastItemIndex);

        this.firstItemIndex = firstItemIndex;
        this.lastItemIndex = lastItemIndex;

        initializeDelegate(capacity, firstItemIndex, lastItemIndex, copyDescriptors);
    }

    /**
     * Initializes the delegate data structures.
     *
     * @param capacity
     *        integer specifying the valid capacity
     *
     * @param firstItemIndex
     *        integer specifying the valid index of the first {@link Item}
     *
     * @param lastItemIndex
     *        integer specifying the valid index of the last {@link Item}
     *
     * @param copyDescriptors
     *        comma separated sequence of
     *        {@link ItemsCopyDescriptor}
     *        descriptors
     */
    protected abstract void initializeDelegate(final int capacity, final int firstItemIndex, final int lastItemIndex,
                                               final ItemsCopyDescriptor... copyDescriptors);

    @Override
    public int getFirstItemIndex() {
        return firstItemIndex;
    }

    @Override
    public void setFirstItemIndex(final int firstItemIndex) {
        this.firstItemIndex = firstItemIndex;
    }

    @Override
    public void incrementFirstItemIndex(final int increment) {
        firstItemIndex += increment;
    }

    @Override
    public int getLastItemIndex() {
        return lastItemIndex;
    }

    @Override
    public void setLastItemIndex(final int lastItemIndex) {
        this.lastItemIndex = lastItemIndex;
    }

    @Override
    public void incrementLastItemIndex(final int increment) {
        lastItemIndex += increment;
    }

    @Override
    public int getItemsCount() {
        return count(firstItemIndex, lastItemIndex);
    }

    @Override
    public int getTailCapacity() {
        return getCapacity() - lastItemIndex - 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AbstractLinearIndexStorage<Item> clone() {
        return (AbstractLinearIndexStorage<Item>) super.clone();
    }

    /**
     * Ensures that the specified capacity and first and last {@link Item} indices are valid values.
     *
     * @param capacity
     *        integer specifying the capacity
     *
     * @param firstItemIndex
     *        integer specifying the index of the first {@link Item}
     *
     * @param lastItemIndex
     *        integer specifying the index of the last {@link Item}
     *
     * @throws LinearIndexStorageException
     *         if the specified values are not valid as defined by
     *         {@link #initialize(int, int, int, ItemsCopyDescriptor...)}
     */
    private void ensureInitializationArgumentsValid(final int capacity, final int firstItemIndex,
                                                    final int lastItemIndex) {
        if (firstItemIndex < 0)
            throw new LinearIndexStorageException(this, "firstItemIndex = {1} < 0", firstItemIndex);

        if (firstItemIndex > lastItemIndex)
            throw new LinearIndexStorageException(this, "lastItemIndex = {2} > {1} = firstItemIndex", firstItemIndex,
                                                  lastItemIndex);

        if (lastItemIndex > capacity - 1)
            throw new LinearIndexStorageException(this, "lastItemIndex = {2} > {1} - 1 = capacity - 1", capacity,
                                                  lastItemIndex);

        if (count(firstItemIndex, lastItemIndex) > capacity)
            throw new LinearIndexStorageException(this,
                                                  "count(firstItemIndex: {2}, lastItemIndex: {3}) = {4} > {1} = capacity",
                                                  capacity, firstItemIndex, lastItemIndex,
                                                  count(firstItemIndex, lastItemIndex));
    }
}
