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
package org.eclipse.persistence.testing.oxm.mappings.directtofield.identifiedbyname.calendartest;

import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;
import java.util.*;

public class CalendarTimeTestIdentifiedByNameTestCases extends XMLMappingTestCases {

  private final static String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/directtofield/identifiedbyname/calendartest/CalendarTimeTestIdentifiedByName.xml";
  private final static int CONTROL_ID = 123;
  private final static String CONTROL_FIRST_NAME = "Jane";
  private final static String CONTROL_LAST_NAME = "Doe";
    private static Calendar CONTROL_BIRTH_DATE;

  public CalendarTimeTestIdentifiedByNameTestCases(String name) throws Exception {
    super(name);
    setControlDocument(XML_RESOURCE);
        setProject(new CalendarTestIdentifiedByNameProject());
  }

  @Override
  protected Object getControlObject() {

        CONTROL_BIRTH_DATE = Calendar.getInstance();
        CONTROL_BIRTH_DATE.clear();
        CONTROL_BIRTH_DATE.set(Calendar.HOUR_OF_DAY, 8);
        CONTROL_BIRTH_DATE.set(Calendar.MINUTE,05);
        CONTROL_BIRTH_DATE.set(Calendar.SECOND,06);

    Employee employee = new Employee();
    employee.setID(CONTROL_ID);
    employee.setFirstName(CONTROL_FIRST_NAME);
    employee.setLastName(CONTROL_LAST_NAME);
        employee.setBirthdate(CONTROL_BIRTH_DATE);
    return employee;
  }

}
