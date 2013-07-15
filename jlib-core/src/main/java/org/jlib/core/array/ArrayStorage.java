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

package org.jlib.core.array;

import static org.jlib.core.array.ArrayUtility.createArray;
import static org.jlib.core.math.MathUtility.count;

import org.jlib.core.storage.AbstractLinearIndexStorage;
import org.jlib.core.storage.InvalidCapacityException;
import org.jlib.core.storage.InvalidIndexException;
import org.jlib.core.storage.LinearIndexStorage;
import org.jlib.core.storage.indexrangeoperation.IndexRangeOperationDescriptor;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;

/**
 * {@link LinearIndexStorage} based on an array.
 *
 * @param <Item>
 *        type of the items stored in the array
 *
 * @author Igor Akkerman
 */

// TODO: add note: ensureIndexValid/ensurePartialCapacityValid methods here have a different meaning than in the strategies!!!!
// TODO: Here, they mean wrong access to the delegate, there, they may mean: wrong item index. maybe separate the exceptions for clarity?
public class ArrayStorage<Item>
extends AbstractLinearIndexStorage<Item>
implements LinearIndexStorage<Item> {

    /** array holding the {@link Item}s */
    private Item[] delegateArray;

    public ArrayStorage(final int initialCapacity)
    throws InvalidCapacityException {
        super(initialCapacity);

        delegateArray = createArray(initialCapacity);
    }

    @Override
    public int getCapacity() {
        return delegateArray.length;
    }

    @Override
    protected Item safeGetItem(final int index) {
        return delegateArray[index];
    }

    @Override
    protected void safeReplaceItem(final int index, final Item item) {
        delegateArray[index] = item;
    }

    @Override
    protected void safeAddCapacityAndShiftItems(final int capacity,
                                                final IndexRangeOperationDescriptor... copyDescriptors) {
        final Item[] newDelegateArray = createArray(delegateArray.length + capacity);

        copyItemsTo(newDelegateArray, copyDescriptors);

        delegateArray = newDelegateArray;
    }

    @Override
    public void shiftItems(final IndexRangeOperationDescriptor... shiftDescriptors)
    throws IndexOutOfBoundsException {
        copyItemsTo(delegateArray, shiftDescriptors);
    }

    private void copyItemsTo(final Item[] targetArray, final IndexRangeOperationDescriptor... copyDescriptors) {
        for (final IndexRangeOperationDescriptor copyDescriptor : copyDescriptors)
            copyItems(delegateArray, targetArray, copyDescriptor);
    }

    /**
     * Copies ranges of {@link Item}s defined by the specified {@link IndexRangeOperationDescriptor} from the specified
     * source to the specified target array.
     *
     * @param sourceArray
     *        source array of {@link Item}s
     *
     * @param targetArray
     *        target array of {@link Item}s
     *
     * @param copyDescriptor
     *        {@link IndexRangeOperationDescriptor} for the operation
     */
    protected void copyItems(final Item[] sourceArray, final Item[] targetArray,
                             final IndexRangeOperationDescriptor copyDescriptor)
    throws InvalidIndexException {

        validateOperationDescriptor(copyDescriptor);

        arraycopy(sourceArray, copyDescriptor.getSourceBeginIndex(), targetArray, copyDescriptor.getTargetIndex(),
                  count(copyDescriptor.getSourceBeginIndex(), copyDescriptor.getSourceEndIndex()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        final ArrayStorage<Item> cloneStorage = (ArrayStorage<Item>) super.clone();

        cloneStorage.delegateArray = copyOf(delegateArray, delegateArray.length);

        return cloneStorage;
    }
}