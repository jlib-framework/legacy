/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2014 Igor Akkerman
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

package org.jlib.core.text.message;

import org.jlib.core.value.formatter.MessageFormatNamedValueFormatter;
import org.jlib.core.value.formatter.PrintfNamedValueFormatter;

import org.assertj.core.api.Assertions;
import static org.jlib.core.text.message.MessageUtility.message;
import static org.jlib.core.value.ValueUtility.named;
import org.junit.Before;
import org.junit.Test;

public class EagerMessageTest {

    private static final MessageConfiguration COLON_PRINTF_CONFIG;

    static {
        COLON_PRINTF_CONFIG = new MessageConfiguration();
        COLON_PRINTF_CONFIG.setArgumentFormatter(new PrintfNamedValueFormatter("%s: %s"));
        COLON_PRINTF_CONFIG.setTextArgumentsSeparator(" ");
        COLON_PRINTF_CONFIG.setArgumentsSeparator("; ");
    }

    private static final MessageConfiguration EQUALS_QUOTE_PRINTF_CONFIG;

    static {
        EQUALS_QUOTE_PRINTF_CONFIG = new MessageConfiguration();
        EQUALS_QUOTE_PRINTF_CONFIG.setArgumentFormatter(new PrintfNamedValueFormatter("%s='%s'"));
        EQUALS_QUOTE_PRINTF_CONFIG.setTextArgumentsSeparator(" ");
        EQUALS_QUOTE_PRINTF_CONFIG.setArgumentsSeparator(" ");
    }

    private static final MessageConfiguration COLON_MF_CONFIG;

    static {
        COLON_MF_CONFIG = new MessageConfiguration();
        COLON_MF_CONFIG.setArgumentFormatter(new MessageFormatNamedValueFormatter("{0}: {1}"));
        COLON_MF_CONFIG.setTextArgumentsSeparator(" ");
        COLON_MF_CONFIG.setArgumentsSeparator("; ");
    }

    @Before
    public void initializeDefaultConfiguration() {
        MessageConfigurationRegistry.getInstance().setDefaultConfiguration(EQUALS_QUOTE_PRINTF_CONFIG);
    }

    @Test
    public void messageWithTextShouldProduceCorrectString() {
        final Message message = message("Something went wrong.");

        Assertions.assertThat(message.toString()).isEqualTo("Something went wrong.");
    }

    @Test
    public void messageWithSingleArgumentShouldProduceCorrectString() {
        final Message message = message().with("dummyName", "Dummy Value");

        Assertions.assertThat(message.toString()).isEqualTo("dummyName='Dummy Value'");
    }

    @Test
    public void messageWithMultipleArgumentsShouldProduceCorrectString() {
        final Message message = message().with("dummyName", 1).with("dummerName", "Dummer Value");

        Assertions.assertThat(message.toString()).isEqualTo("dummyName='1' dummerName='Dummer Value'");
    }

    @Test
    public void messageWithTextAndArgumentsShouldProduceCorrectString() {
        final Message message = /*
         */ message("Something went wrong.").with("dummyName", 1).with("dummerName", "Dummer Value");

        Assertions.assertThat(message.toString())
                  .isEqualTo("Something went wrong. dummyName='1' dummerName='Dummer Value'");
    }

    @Test
    public void thrownExceptionShouldHaveTextMessageWithNamedArgument() {
        final Message message = message("Something went wrong.").with(named("dummyName", 1));

        Assertions.assertThat(message.toString()).isEqualTo("Something went wrong. dummyName='1'");
    }

    @Test
    public void thrownExceptionShouldHaveTextMessageWithNamedArgumentsInSpecifiedDefaultFormat() {
        MessageConfigurationRegistry.getInstance().setDefaultConfiguration(COLON_PRINTF_CONFIG);

        final Message message = /*
         */ message("Something went wrong.").with(named("dummyName", 1), named("dummerName", "Dummer Value"));

        Assertions.assertThat(message.toString())
                  .isEqualTo("Something went wrong. dummyName: 1; dummerName: Dummer Value");
    }

    @Test
    public void thrownExceptionShouldHaveTextMessageWithNamedArguments() {
        final Message message = /*
         */ message("Something went wrong.").with(named("dummyName", 1), named("dummerName", "Dummer Value"));

        Assertions.assertThat(message.toString())
                  .isEqualTo("Something went wrong. dummyName='1' dummerName='Dummer Value'");
    }

    @Test
    public void thrownExceptionShouldHaveTextMessageWithArgumentsInSpecifiedPrintfFormat() {
        final Message message = /*
         */ message("Something went wrong.", COLON_PRINTF_CONFIG).with("dummyName", 1)
                                                                 .with("dummerName", "Dummer Value");

        Assertions.assertThat(message.toString())
                  .isEqualTo("Something went wrong. dummyName: 1; dummerName: Dummer Value");
    }

    @Test
    public void thrownExceptionShouldHaveTextMessageWithArgumentsInSpecifiedMfFormat() {
        final Message message = /*
          */ message("Something went wrong.", COLON_MF_CONFIG).with("dummyName", 1).with("dummerName", "Dummer Value");

        Assertions.assertThat(message.toString())
                  .isEqualTo("Something went wrong. dummyName: 1; dummerName: Dummer Value");
    }
}