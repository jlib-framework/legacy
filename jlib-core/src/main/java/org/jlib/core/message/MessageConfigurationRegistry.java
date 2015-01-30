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

package org.jlib.core.message;

import org.jlib.core.value.formatter.PrintfNamedValueFormatter;

public class MessageConfigurationRegistry {

    private static final MessageConfigurationRegistry INSTANCE = new MessageConfigurationRegistry();

    public static MessageConfigurationRegistry getInstance() {
        return INSTANCE;
    }

    private MessageConfiguration defaultConfiguration = createInitialDefaultConfiguration();

    private MessageConfigurationRegistry() {}

    public MessageConfiguration getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public void setDefaultConfiguration(final MessageConfiguration defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    private MessageConfiguration createInitialDefaultConfiguration() {
        final MessageConfiguration defaultConfiguration = new MessageConfiguration();

        defaultConfiguration.setArgumentFormatter(new PrintfNamedValueFormatter("%s='%s'"));
        defaultConfiguration.setTextArgumentsSeparator(" ");
        defaultConfiguration.setArgumentsSeparator(" ");

        return defaultConfiguration;
    }
}
