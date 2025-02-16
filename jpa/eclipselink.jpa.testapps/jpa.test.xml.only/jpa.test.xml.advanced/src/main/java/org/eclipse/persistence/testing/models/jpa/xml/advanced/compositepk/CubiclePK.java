/*
 * Copyright (c) 1998, 2022 Oracle and/or its affiliates. All rights reserved.
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

package org.eclipse.persistence.testing.models.jpa.xml.advanced.compositepk;

import java.util.Objects;

public class CubiclePK {
    public int id;
    public String code;

    public CubiclePK(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public boolean equals(Object other) {
        if (other instanceof CubiclePK) {
            final CubiclePK otherCubiclePK = (CubiclePK) other;
            return (otherCubiclePK.id == id && otherCubiclePK.code.equals(code));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
