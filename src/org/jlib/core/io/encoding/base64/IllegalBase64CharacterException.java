/*
 * jlib - The Free Java Library
 * 
 *    http://www.jlib.org
 *    
 * Copyright (c) 2006-2008 Igor Akkerman
 * 
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.core.io.encoding.base64;


/**
 * Exception thrown if an illegal base64 character is read in a base64 input.
 *
 * @author Igor Akkerman
 */

public class IllegalBase64CharacterException
extends InvalidBase64StreamException {

    /** illegal character */
    int illegalCharacter;

    /**
     * Creates a new IllegalBase64CharacterException.
     *
     * @param illegalCharacter
     *        integer specifying the illegal character;
     *        -1 if the end of the base64 encoded stream has been reached with
     *        missing characters
     */
    public IllegalBase64CharacterException(int illegalCharacter) {
        super();
        this.illegalCharacter = illegalCharacter;
    }

    /**
     * Returns the illegal character.
     *
     * @return integer specifying the illegal character;
     *         -1 if the end of the base64 encoded stream has been reached with
     *         missing characters
     */
    public int getIllegalCharacter() {
        return illegalCharacter;
    }

    // @see java.lang.Throwable#toString()
    @Override
    public String toString() {
        return super.toString() + "[" + illegalCharacter + "]";
    }
}
