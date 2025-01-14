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
package org.eclipse.persistence.testing.oxm.mappings.sequenced;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.oxm.sequenced.*;
import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;

public class GroupingElementSharedTestCases extends XMLMappingTestCases {

    private static final String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/sequenced/GroupingElementShared.xml";
    private static final String CONTROL_EMPLOYEE_FIRST_NAME = "Jane";
    private static final String CONTROL_EMPLOYEE_LAST_NAME = "Doe";

    private static final EmployeeProject EMPLOYEE_PROJECT = new EmployeeProject();

    public GroupingElementSharedTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);
        setProject(EMPLOYEE_PROJECT);
    }

    @Override
    public Object getControlObject() {
        SequencedObject controlEmployee = new Employee();

        Setting personalInfoSetting = new Setting(null, "personal-info");
        controlEmployee.getSettings().add(personalInfoSetting);

        Setting firstNameSetting = new Setting("urn:example", "first-name");
        personalInfoSetting.addChild(firstNameSetting);

        Setting firstNameTextSetting = new Setting(null, "text()");
        firstNameTextSetting.setObject(controlEmployee);
        DatabaseMapping firstNameMapping = EMPLOYEE_PROJECT.getDescriptor(Employee.class).getMappingForAttributeName("firstName");
        firstNameTextSetting.setMapping(firstNameMapping);
        firstNameTextSetting.setValue(CONTROL_EMPLOYEE_FIRST_NAME);
        firstNameSetting.addChild(firstNameTextSetting);

        Setting lastNameSetting = new Setting(null, "last-name");
        personalInfoSetting.addChild(lastNameSetting);

        Setting lastNameTextSetting = new Setting(null, "text()");
        lastNameTextSetting.setObject(controlEmployee);
        DatabaseMapping lastNameMapping = EMPLOYEE_PROJECT.getDescriptor(Employee.class).getMappingForAttributeName("lastName");
        lastNameTextSetting.setMapping(lastNameMapping);
        lastNameTextSetting.setValue(CONTROL_EMPLOYEE_LAST_NAME);
        lastNameSetting.addChild(lastNameTextSetting);

        return controlEmployee;
    }

    @Override
    public void xmlToObjectTest(Object testObject) throws Exception {
        Employee testEmployee = (Employee) testObject;
        assertEquals(1, testEmployee.getSettings().size());
        super.xmlToObjectTest(testObject);
    }

}
