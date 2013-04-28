package org.jlib.container.sequence.index.array.storage;

import static org.jlib.core.math.MathUtility.count;

/**
 * {@link LinearIndexStorageCapacityStrategy} providing just as much capacity as
 * requested.
 * 
 * @param <Item>
 *        type of the {@link LinearIndexStorage} Items
 * 
 * @author Igor Akkerman
 */
public class MinimalLinearIndexStorageCapacityStrategy<Item>
implements LinearIndexStorageCapacityStrategy {

    /** {@link LinearIndexStorage} holding the Items */
    private final LinearIndexStorage<Item> storage;

    /**
     * Creates a new {@link MinimalLinearIndexStorageCapacityStrategy}.
     * 
     * @param storage
     *        referenced {@link LinearIndexStorage}
     */
    public MinimalLinearIndexStorageCapacityStrategy(final LinearIndexStorage<Item> storage) {
        super();

        this.storage = storage;
    }

    @Override
    public void initialize(final int firstItemIndex, final int lastItemIndex) {
        storage.initialize(count(firstItemIndex, lastItemIndex), firstItemIndex, lastItemIndex);
    }

    @Override
    public void ensureHeadCapacity(final int headCapacity)
    throws LinearIndexStorageException {
        assertPartCapacityValid("middleCapacity", headCapacity);

        if (headCapacity <= storage.getFirstItemIndex())
            return;

        storage.initialize(headCapacity + storage.getCapacity() - storage.getFirstItemIndex(),
                           storage.getFirstItemIndex(), storage.getLastItemIndex(),
                           new ItemsCopy(storage.getFirstItemIndex(), storage.getLastItemIndex(), headCapacity));
    }

    @Override
    public void ensureTailCapacity(final int tailCapacity)
    throws LinearIndexStorageException {
        assertPartCapacityValid("middleCapacity", tailCapacity);

        if (tailCapacity <= storage.getTailCapacity())
            return;

        storage.initialize(storage.getLastItemIndex() + 1 + tailCapacity,
                           storage.getFirstItemIndex(),
                           storage.getLastItemIndex(),
                           new ItemsCopy(storage.getFirstItemIndex(), storage.getLastItemIndex(),
                                         storage.getFirstItemIndex()));
    }

    @Override
    public void ensureMiddleCapacity(final int splitIndex, final int middleCapacity)
    throws LinearIndexStorageException {
        assertPartCapacityValid("middleCapacity", middleCapacity);

        if (middleCapacity < 0)
            throw new LinearIndexStorageException(storage, "middleCapacity = {1} < 0; storage = '{0}'", middleCapacity);

        if (splitIndex < storage.getFirstItemIndex())
            throw new LinearIndexStorageException(storage, "splitIndex = {1} > {2} = firstIndex; storage = '{0}'",
                                                  splitIndex, storage.getFirstItemIndex());

        if (splitIndex > storage.getLastItemIndex())
            throw new LinearIndexStorageException(storage, "splitIndex = {1} < {2} = lastIndex; storage = '{0}'",
                                                  splitIndex, storage.getLastItemIndex());

        if (middleCapacity == 0)
            return;

        final ItemsCopy rightCopyDescriptor =
            new ItemsCopy(splitIndex, storage.getLastItemIndex(), splitIndex + middleCapacity);

        final int newLastItemIndex = storage.getLastItemIndex() + middleCapacity;

        if (storage.getTailCapacity() >= middleCapacity) {
            storage.shiftItems(new ItemsCopy(splitIndex, storage.getLastItemIndex(), splitIndex + middleCapacity));
            storage.setLastItemIndex(newLastItemIndex);
            return;
        }

        final int fullCapacity = storage.getItemsCount() + middleCapacity;

        if (splitIndex > storage.getFirstItemIndex())
            storage.initialize(fullCapacity, storage.getFirstItemIndex(), newLastItemIndex,
                               new ItemsCopy(storage.getFirstItemIndex(), splitIndex - 1, splitIndex),
                               rightCopyDescriptor);
        else
            storage.initialize(fullCapacity, storage.getFirstItemIndex(), newLastItemIndex, rightCopyDescriptor);
    }

    /**
     * Asserts that the specified partial capacity is valid.
     * 
     * @param partialCapacityName
     *        String specifying the name of the partial capacity
     * 
     * @param partialCapacity
     *        integer specifying the partial capacity
     * 
     * @throws LinearIndexStorageException
     *         if {@code partialCapacity < 0}
     */
    private void assertPartCapacityValid(final String partialCapacityName, final int partialCapacity) {
        if (partialCapacity < 0)
            throw new LinearIndexStorageException(storage, "{1} = {2} < 0; storage = '{0}'", partialCapacityName,
                                                  partialCapacity);
    }

}