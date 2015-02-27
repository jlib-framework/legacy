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

package org.jlib.reflect.programtarget.reflect_new;

import java.lang.reflect.Method;

import org.jlib.reflect.programtarget.MethodLookupException;
import org.jlib.reflect.programtarget.MethodReturnValueHolder;
import org.jlib.reflect.programtarget.NoSubtypeException;
import static org.jlib.reflect.programtarget.factory.Factories.methodReturnValueFactory;
import org.jlib.reflect.reflector.MethodReturnValueReflector;
import org.jlib.reflect.validator.MethodReturnTypeValidator;

public class DefaultMethod0<ReturnValue>
extends AbstractMethod<ReturnValue>
implements Method0<ReturnValue> {

    public DefaultMethod0(final MethodReturnTypeValidator methodReturnTypeValidator) {
        super(methodReturnTypeValidator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public MethodReturnValueReflector<ReturnValue> invoke(final Method method)
    throws MethodLookupException {
        final Object returnValue = getMethodInvoker().invoke();

        return methodReturnValueFactory().methodReturnValue(returnValue, method);
    }

    @Override
    public Method0<ReturnValue> assertReturns(final Class<ReturnValue> staticReturnSuperType)
    throws NoSubtypeException {
        methodReturnTypeValidator.assertReturns(staticReturnSuperType);

        return this;
    }

    @Override
    public Method get()
    throws MethodLookupException {
        // FIXME: implement
        return null;
    }
}
