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

import org.jlib.container.iterator.iterator.NoNextItemException;

/**
 * {@link NoNextItemException} thrown when there is no next Item to return by a
 * {@link SequenceIterator}.
 *
 * @author Igor Akkerman
 */
public class NoNextSequenceItemException
extends NoNextItemException {

    private static final long serialVersionUID = 3286617731417853890L;

    /**
     * Creates a new {@link NoNextSequenceItemException}.
     *
     * @param sequence
     *        traversed {@link Sequence}
     */
    public NoNextSequenceItemException(final Sequence<?> sequence) {
        this(sequence, null);
    }

    /**
     * Creates a new {@link NoNextSequenceItemException}.
     *
     * @param sequence
     *        traversed {@link Sequence}
     *
     * @param cause
     *        {@link Exception} that caused this {@link Exception}
     */

    public NoNextSequenceItemException(final Sequence<?> sequence, final Exception cause) {
        super(sequence, cause);
    }
}
