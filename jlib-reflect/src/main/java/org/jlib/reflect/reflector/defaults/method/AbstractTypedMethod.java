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

package org.jlib.reflect.reflector.defaults.method;

import java.lang.reflect.Executable;

import org.jlib.reflect.programelement.MethodInvoker;
import org.jlib.reflect.reflector.MethodReturn;
import org.jlib.reflect.reflector.TypedMethod;
import org.jlib.reflect.reflector.supplier.MethodReturnSupplier;
import org.jlib.reflect.reflector.supplier.TypedMethodSupplier;

public abstract class AbstractTypedMethod<ReturnValue>
implements TypedMethod<ReturnValue> {

    private TypedMethodSupplier typedMethodSupplier;
    private MethodReturnSupplier methodReturnSupplier;
    private MethodInvoker methodInvoker;

    protected MethodInvoker getMethodInvoker() {
        return methodInvoker;
    }

    @Override
    public Executable get() {
        return methodInvoker.getMethod();
    }

    public MethodReturn<ReturnValue> methodReturnValue(final ReturnValue returnValue) {
        return methodReturnSupplier.methodReturnValue(returnValue, getMethodInvoker());
    }

    protected TypedMethodSupplier getTypedMethodSupplier() {
        return typedMethodSupplier;
    }

    public void setTypedMethodSupplier(final TypedMethodSupplier typedMethodSupplier) {
        this.typedMethodSupplier = typedMethodSupplier;
    }

    protected MethodReturnSupplier getMethodReturnSupplier() {
        return methodReturnSupplier;
    }

    public void setMethodReturnSupplier(final MethodReturnSupplier methodReturnSupplier) {
        this.methodReturnSupplier = methodReturnSupplier;
    }

    public void setMethodInvoker(final MethodInvoker methodInvoker) {
        this.methodInvoker = methodInvoker;
    }
}
