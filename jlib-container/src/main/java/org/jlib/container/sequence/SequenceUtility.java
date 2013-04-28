package org.jlib.container.sequence;

import java.util.Iterator;

import org.jlib.core.array.ArrayUtility;
import org.jlib.core.observer.ValueObserver;
import org.jlib.core.traverser.Traverser;
import org.jlib.core.traverser.TwoWayTraversible;

/**
 * Facade utility for {@link Sequence} creation and operations.
 * 
 * @author Igor Akkerman
 */
public final class SequenceUtility {

    /** no visible default constructor */
    private SequenceUtility() {}

    /**
     * Creates a new {@link Sequence} containing solely the specified Item.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param item
     *        sole Item of the new {@link Sequence}
     * 
     * @return new singleton {@link Sequence}
     */
    public static <Item> Sequence<Item> singleton(final Item item) {
        return new SingletonSequence<Item>(item);
    }

    /**
     * Appends the specified Item to the specified {@link AppendSequence} that
     * does not yet contain the Item.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link AppendSequence} to which the Item is appended
     * 
     * @param item
     *        appended Item
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of {@code item} prevents it from being appended
     *         to {@code sequence}
     */
    public static <Item> void append(final AppendSequence<Item> sequence, final Item item)
    throws IllegalSequenceArgumentException {
        sequence.append(item);
    }

    /**
     * Appends all Items provided by the specified {@link Iterable} to the
     * specified {@link AppendSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link AppendSequence} to which the Items are appended
     * 
     * @param items
     *        {@link Iterable} providing the Items to append
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being appended to {@code sequence}
     */
    public static <Item> void append(final AppendSequence<Item> sequence, final Iterable<? extends Item> items) {
        for (final Item item : items)
            sequence.append(item);
    }

    /**
     * Appends all Items in the specified comma separated sequence to the
     * specified {@link AppendSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link AppendSequence} to which the Items are appended
     * 
     * @param items
     *        {@link Iterable} providing the Items to append
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being appended to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void append(final AppendSequence<Item> sequence, final Item... items)
    throws IllegalSequenceArgumentException {
        append(sequence, ArrayUtility.iterable(items));
    }

    /**
     * Appends the specified Item to the specified {@link AppendSequence} that
     * does not yet contain the Item.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link ObservedAppendSequence} to which the Item is appended
     * 
     * @param item
     *        appended Item
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of {@code item} prevents it from being appended
     *         to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void append(final ObservedAppendSequence<Item> sequence, final Item item,
                                     final ValueObserver<Item>... observers)
    throws IllegalSequenceArgumentException {
        sequence.append(item, observers);
    }

    /**
     * Appends all Items provided by the specified {@link Iterable} to the
     * specified {@link AppendSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link ObservedAppendSequence} to which the Items are appended
     * 
     * @param items
     *        {@link Iterable} providing the Items to append
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being appended to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void append(final ObservedAppendSequence<Item> sequence, final Iterable<? extends Item> items,
                                     final ValueObserver<Item>... observers) {
        for (final Item item : items)
            sequence.append(item, observers);
    }

    /**
     * Appends all Items in the specified comma separated sequence to the
     * specified {@link AppendSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link ObservedAppendSequence} to which the Items are appended
     * 
     * @param items
     *        {@link Iterable} providing the Items to append
     * 
     * @param observers
     *        array of {@link ValueObserver} instances attending the operation
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being appended to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void append(final ObservedAppendSequence<Item> sequence,
                                     final ValueObserver<Item>[] observers, final Item... items)
    throws IllegalSequenceArgumentException {
        append(sequence, ArrayUtility.iterable(items), observers);
    }

    /**
     * Prepends the specified Item to the specified {@link PrependSequence} that
     * does not yet contain the Item.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link PrependSequence} to which the Item is prepended
     * 
     * @param item
     *        prepended Item
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of {@code item} prevents it from being prepended
     *         to {@code sequence}
     */
    public static <Item> void prepend(final PrependSequence<Item> sequence, final Item item)
    throws IllegalSequenceArgumentException {
        sequence.prepend(item);
    }

    /**
     * Prepends all Items provided by the specified {@link Iterable} to the
     * specified {@link PrependSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link PrependSequence} to which the Items are prepended
     * 
     * @param items
     *        {@link Iterable} providing the Items to prepend
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being prepended to {@code sequence}
     */
    public static <Item> void prepend(final PrependSequence<Item> sequence, final Iterable<? extends Item> items) {
        for (final Item item : items)
            sequence.prepend(item);
    }

    /**
     * Prepends all Items in the specified comma separated sequence to the
     * specified {@link PrependSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link PrependSequence} to which the Items are prepended
     * 
     * @param items
     *        {@link Iterable} providing the Items to prepend
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being prepended to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void prepend(final PrependSequence<Item> sequence, final Item... items)
    throws IllegalSequenceArgumentException {
        prepend(sequence, ArrayUtility.iterable(items));
    }

    /**
     * Prepends the specified Item to the specified {@link PrependSequence} that
     * does not yet contain the Item.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link ObservedPrependSequence} to which the Item is prepended
     * 
     * @param item
     *        prepended Item
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of {@code item} prevents it from being prepended
     *         to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void prepend(final ObservedPrependSequence<Item> sequence, final Item item,
                                      final ValueObserver<Item>... observers)
    throws IllegalSequenceArgumentException {
        sequence.prepend(item, observers);
    }

    /**
     * Prepends all Items provided by the specified {@link Iterable} to the
     * specified {@link PrependSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link ObservedPrependSequence} to which the Items are prepended
     * 
     * @param items
     *        {@link Iterable} providing the Items to prepend
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the operation
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being prepended to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void prepend(final ObservedPrependSequence<Item> sequence,
                                      final Iterable<? extends Item> items, final ValueObserver<Item>... observers) {
        for (final Item item : items)
            sequence.prepend(item, observers);
    }

    /**
     * Prepends all Items in the specified comma separated sequence to the
     * specified {@link PrependSequence}.
     * 
     * @param <Item>
     *        type of the items held in the {@link Sequence}
     * 
     * @param sequence
     *        {@link ObservedPrependSequence} to which the Items are prepended
     * 
     * @param items
     *        {@link Iterable} providing the Items to prepend
     * 
     * @param observers
     *        array of {@link ValueObserver} instances attending the operation
     * 
     * @throws IllegalSequenceArgumentException
     *         if some property of one Item in {@code items} prevents it from
     *         being prepended to {@code sequence}
     */
    @SafeVarargs
    public static <Item> void prepend(final ObservedPrependSequence<Item> sequence,
                                      final ValueObserver<Item>[] observers, final Item... items)
    throws IllegalSequenceArgumentException {
        prepend(sequence, ArrayUtility.iterable(items), observers);
    }

    /**
     * Returns a concatenated view of the specified {@link TwoWayTraversible}
     * instances. The behaviour of the returned {@link Sequence} and its
     * {@link Traverser} or {@link Iterator} is unspecified if one of the
     * {@link TwoWayTraversible} instances is modified.
     * 
     * @param <Item>
     *        type of the items provided by {@code traversibles}
     * 
     * @param traversibles
     *        comma separated sequence of {@link TwoWayTraversible} instances
     * 
     * @return concatenated {@link Sequence} view
     */
    @SafeVarargs
    public static <Item> Sequence<Item> concatenated(final TwoWayTraversible<Item>... traversibles) {
        return new ConcatenatedSequence<>(traversibles);
    }

    /**
     * Returns the specified sequence size if it is positive.
     * 
     * @param size
     *        positive integer specifying the sequence size
     * 
     * @return {@code size} if {@code size >= 1}
     * 
     * @throws InvalidSequenceItemsCountException
     *         if {@code size < 1}
     */
    public static int getValidatedSequenceItemsCount(final int size)
    throws InvalidSequenceItemsCountException {
        if (size < 1)
            throw new InvalidSequenceItemsCountException(size, "size == {0} < 1");

        return size;
    }
}