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

package org.jlib.container.sequence.index.array;

import java.util.Collection;

import org.jlib.container.Container;
import org.jlib.container.sequence.InvalidSequenceItemsCountException;
import org.jlib.container.sequence.index.AbstractInitializeableIndexSequence;
import org.jlib.container.sequence.index.InvalidSequenceIndexRangeException;
import org.jlib.container.sequence.index.array.storage.LinearIndexStorage;
import org.jlib.container.sequence.index.array.storage.LinearIndexStorageCapacityStrategy;
import org.jlib.container.sequence.index.array.storage.MinimalLinearIndexStorageCapacityStrategy;

public abstract class LinearIndexStorageSequence<Item>
extends AbstractInitializeableIndexSequence<Item> {

    /** {@link LinearIndexStorage} used to store the {@link Item}s */
    private LinearIndexStorage<Item> storage;

    /** {@link LinearIndexStorageCapacityStrategy} used to adjust the {@link LinearIndexStorage} capacity */
    private LinearIndexStorageCapacityStrategy capacityStrategy;

    public LinearIndexStorageSequence(final int firstIndex, final Item... items) {super(firstIndex, items);}

    public LinearIndexStorageSequence(final Item... items) {super(items);}

    public LinearIndexStorageSequence(final int firstIndex, final int lastIndex)
    throws InvalidSequenceIndexRangeException {super(firstIndex, lastIndex);}

    public LinearIndexStorageSequence(final Container<? extends Item> items) {super(items);}

    public LinearIndexStorageSequence(final Collection<? extends Item> items) {super(items);}

    public LinearIndexStorageSequence(final int firstIndex, final Container<? extends Item> items) {
        super(firstIndex, items);
    }

    public LinearIndexStorageSequence(final int firstIndex, final Collection<? extends Item> items) {
        super(firstIndex, items);
    }

    public LinearIndexStorageSequence(final int itemsCount)
    throws InvalidSequenceItemsCountException {super(itemsCount);}

    protected void initializeCapacityStrategy(final LinearIndexStorage<Item> storage) {
        capacityStrategy = new MinimalLinearIndexStorageCapacityStrategy<>(storage);
    }

    @Override
    protected Item getStoredItem(final int index) {
        return storage.getItem(getStorageItemIndex(index));
    }

    @Override
    protected void replaceStoredItem(final int index, final Item newItem) {
        storage.replaceItem(getStorageItemIndex(index), newItem);
    }

    /**
     * Returns the {@link LinearIndexStorage} index in the specified index in
     * this {@link ArraySequence}.
     *
     * @param index
     *        integer specifying the index of the {@link Item} in the
     *        {@link ArraySequence}
     *
     * @return integer specifying the corresponding index in the delegate
     *         {@link LinearIndexStorage}
     */
    protected int getStorageItemIndex(final int index) {
        return index - getFirstIndex() + storage.getFirstItemIndex();
    }

    /**
     * Returns the {@link LinearIndexStorageCapacityStrategy} used by this
     * {@link ArraySequence}.
     *
     * @return used {@link LinearIndexStorageCapacityStrategy}
     */
    protected LinearIndexStorageCapacityStrategy getCapacityStrategy() {
        return capacityStrategy;
    }

    /**
     * Registers the {@link LinearIndexStorageCapacityStrategy} used by this
     * {@link ArraySequence}.
     *
     * @param capacityStrategy
     *        used {@link LinearIndexStorageCapacityStrategy}
     */
    protected void setCapacityStrategy(final LinearIndexStorageCapacityStrategy capacityStrategy) {
        this.capacityStrategy = capacityStrategy;
    }

    @Override
    protected void initialize() {
        storage = createStorage();
        initializeCapacityStrategy(storage);

        storage.initialize(getItemsCount(), getFirstIndex(), getLastIndex());
    }

    protected abstract LinearIndexStorage<Item> createStorage();

    /**
     * Returns the {@link LinearIndexStorage} used by this {@link ArraySequence}
     * .
     *
     * @return used {@link LinearIndexStorage}
     */
    protected LinearIndexStorage<Item> getStorage() {
        return storage;
    }

    @Override
    public LinearIndexStorageSequence<Item> clone() {
        final LinearIndexStorageSequence<Item> clonedSequence = (LinearIndexStorageSequence<Item>) super.clone();

        clonedSequence.storage = storage.clone();

        return clonedSequence;
    }
}
