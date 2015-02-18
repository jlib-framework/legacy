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

package org.jlib.core.classinstance;

import org.jlib.core.exception.ApplicationException;
import org.jlib.core.message.Message;

/**
 * {@link ApplicationException} thrown when a method cannot be identified by its name in a reflective process.
 *
 * @author Igor Akkerman
 */
public class InvalidMethodException
extends ApplicationException {

    private static final long serialVersionUID = - 3809968471293132878L;

    private final String className;
    private final String methodName;

    public InvalidMethodException(final Message message, final String className, final String methodName) {
        super(message.with("class", className)
                     .with("method", methodName));

        this.className = className;
        this.methodName = methodName;
    }

    public InvalidMethodException(final Message message, final String className, final String methodName,
                                  final Exception cause) {
        this(message, className, methodName);

        initCause(cause);
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}
