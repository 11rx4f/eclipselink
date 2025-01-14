/*
 * Copyright (c) 1998, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.tools.schemaframework;

import java.io.*;
import org.eclipse.persistence.exceptions.ValidationException;
import org.eclipse.persistence.internal.helper.Helper;
import org.eclipse.persistence.internal.sessions.AbstractSession;

/**
 * <p>
 * <b>Purpose</b>: Allow a semi-generic way of creating store function.
 * Note that stored functions supported only on Oracle platform
 * </p>
 */
public class StoredFunctionDefinition extends StoredProcedureDefinition {
    public StoredFunctionDefinition() {
        super();
        this.addOutputArgument(new FieldDefinition());
    }

    /**
     * INTERNAL:
     * Return the create statement.
     */
    @Override
    public Writer buildCreationWriter(AbstractSession session, Writer writer) throws ValidationException {
        if (session.getPlatform().supportsStoredFunctions()) {
            super.buildCreationWriter(session, writer);
        } else {
            throw ValidationException.platformDoesNotSupportStoredFunctions(Helper.getShortClassName(session.getPlatform()));
        }
        return writer;
    }

    /**
     * INTERNAL:
     * Return the drop statement.
     */
    @Override
    public Writer buildDeletionWriter(AbstractSession session, Writer writer) throws ValidationException {
        if (session.getPlatform().supportsStoredFunctions()) {
            super.buildDeletionWriter(session, writer);
        } else {
            throw ValidationException.platformDoesNotSupportStoredFunctions(Helper.getShortClassName(session.getPlatform()));
        }
        return writer;
    }

    /**
     *
     */
    @Override
    public String getCreationHeader() {
        return "CREATE FUNCTION ";
    }

    /**
     *
     */
    @Override
    public String getDeletionHeader() {
        return "DROP FUNCTION ";
    }

    /**
     *
     */
    @Override
    public int getFirstArgumentIndex() {
        return 1;
    }

    /**
     * Prints return for stored function
     */
    public void setReturnType(Class<?> type) {
        FieldDefinition argument = getArguments().get(0);
        argument.setType(type);
    }

    /**
     * Prints return for stored function
     */
    @Override
    protected void printReturn(Writer writer, AbstractSession session) throws ValidationException {
        try {
            session.getPlatform().printStoredFunctionReturnKeyWord(writer);
            FieldDefinition argument = getArguments().get(0);

            // argumentType should be OUT: getArgumentTypes().firstElement() == OUT;
            // but should be printed as IN
            printArgument(argument, writer, session);
            writer.write("\n");
        } catch (IOException ioException) {
            throw ValidationException.fileError(ioException);
        }
    }
}
