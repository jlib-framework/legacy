/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2015 Igor Akkerman
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

package org.jlib.object_spi.apachecommons;

import org.apache.commons.lang3.builder.ToStringStyle;

import org.jlib.core.reflection.ClassInstantiationException;

import static org.jlib.core.reflection.ReflectionUtility.newInstanceOf;

public class ClassNameToStringStyleSupplier
implements ToStringStyleSupplier {

    private final String className;

    public ClassNameToStringStyleSupplier(final String className) {
        this.className = className;
    }

    @Override
    public ToStringStyle getToStringStyle()
    throws ToStringStyleNotFoundException {
        try {
            return newInstanceOf(className, ToStringStyle.class);
        }
        catch (final ClassInstantiationException exception) {
            throw new ToStringStyleNotFoundException(exception);
        }
    }
}
