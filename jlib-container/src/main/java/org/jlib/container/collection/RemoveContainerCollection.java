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

package org.jlib.container.collection;

import java.util.Collection;

import org.jlib.container.ReadContainer;
import org.jlib.container.ObservedDirectRemoveContainer;
import org.jlib.container.RemoveContainer;

/**
 * Adapter allowing a {@link RemoveContainer} to be used as a {@link Collection}.
 * A {@link RemoveContainerCollection} is backed by a {@link RemoveContainer}
 * specified at initialization.
 *
 * @param <Item>
 *        type of the items held in the {@link Collection}
 *
 * @author Igor Akkerman
 */
public class RemoveContainerCollection<Item>
extends ContainerCollection<Item> {

    /** adapted and backed {@link ObservedDirectRemoveContainer} */
    private final ObservedDirectRemoveContainer<Item> delegateContainer;

    /**
     * Creates a new {@link RemoveContainerCollection} backed by the specified
     * {@link RemoveContainer}.
     *
     * @param <DelegateContainer>
     *        type of the delegate {@link ReadContainer}
     *
     * @param delegateContainer
     *        {@link RemoveContainer} backing this
     *        {@link RemoveContainerCollection}
     */
    public <DelegateContainer extends ReadContainer<Item> & ObservedDirectRemoveContainer<Item>> //
    RemoveContainerCollection(final DelegateContainer delegateContainer) {
        super(delegateContainer);

        this.delegateContainer = delegateContainer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(final Object item) {
        delegateContainer.remove((Item) item);

        // TODO: implement modification listener model
        return true;
    }
}
