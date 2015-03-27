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

package org.jlib.reflect.programelement.reflection;

import java.lang.reflect.Constructor;

import org.jlib.reflect.programelement.ConstructorLookup;
import org.jlib.reflect.programelement.InvalidMethodParameterTypesException;

public class ReflectionConstructorLookup
implements ConstructorLookup {

    @Override
    public <EnclosingClassObject>
    Constructor<EnclosingClassObject> lookupConstructor(final Class<EnclosingClassObject> enclosingClass,
                                                        final Class<?>... parameterTypes)
    throws InvalidMethodParameterTypesException {
        try {
            return enclosingClass.getConstructor(parameterTypes);
        }
        catch (final NoSuchMethodException exception) {
            throw new InvalidMethodParameterTypesException(enclosingClass.getName(), enclosingClass.getName(),
                                                           parameterTypes,exception);
        }
    }
}