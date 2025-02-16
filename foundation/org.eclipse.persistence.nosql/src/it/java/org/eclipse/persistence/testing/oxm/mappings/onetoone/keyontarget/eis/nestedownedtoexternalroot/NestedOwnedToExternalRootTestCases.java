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
package org.eclipse.persistence.testing.oxm.mappings.onetoone.keyontarget.eis.nestedownedtoexternalroot;

import java.util.ArrayList;
import java.util.Vector;

import junit.framework.TestCase;
import org.eclipse.persistence.eis.interactions.XQueryInteraction;
import org.eclipse.persistence.internal.eis.adapters.xmlfile.XMLFileInteractionSpec;
import org.eclipse.persistence.testing.oxm.mappings.EISMappingTestCases;
import org.eclipse.persistence.testing.oxm.mappings.onetoone.keyontarget.*;

public class NestedOwnedToExternalRootTestCases extends EISMappingTestCases {
    private final static String XML_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/onetoone/keyontarget/eis/nestedownedtoexternalroot/writing/company_control.xml";
    private final static String XML_TEST_RESOURCE = "org/eclipse/persistence/testing/oxm/mappings/onetoone/keyontarget/eis/nestedownedtoexternalroot/writing/company.xml";
    private final static String CONTROL_EMPLOYEE1_NAME = "Jane";
    private final static String CONTROL_EMPLOYEE2_NAME = "Bob";
    private final static long CONTROL_PROJECT1_ID = 1;
    private final static String CONTROL_PROJECT1_NAME = "Project1";
    private final static long CONTROL_PROJECT2_ID = 2;
    private final static String CONTROL_PROJECT2_NAME = "Project2";
    private final static long CONTROL_PROJECT3_ID = 3;
    private final static String CONTROL_PROJECT3_NAME = "Project3";

    public NestedOwnedToExternalRootTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);
        setProject(new NestedOwnedToExternalRootProject());
    }

    @Override
    protected Object getControlObject() {
        Company company = new Company();
        company.setName("SomeCompany");

        Department dept = new Department();
        dept.setDeptName("TheDepartment");

        Project project1 = new Project();
        project1.setId(CONTROL_PROJECT1_ID);
        project1.setName(CONTROL_PROJECT1_NAME);

        Project project2 = new Project();
        project2.setId(CONTROL_PROJECT2_ID);
        project2.setName(CONTROL_PROJECT2_NAME);

        Project project3 = new Project();
        project3.setId(CONTROL_PROJECT3_ID);
        project3.setName(CONTROL_PROJECT3_NAME);

        Employee employee1 = new Employee();
        employee1.setFirstName(CONTROL_EMPLOYEE1_NAME);
        employee1.setProject(project3);

        Employee employee2 = new Employee();
        employee2.setFirstName(CONTROL_EMPLOYEE2_NAME);
        employee2.setProject(project2);

        dept.addEmployee(employee1);
                dept.addEmployee(employee2);
        company.addDepartment(dept);

        ArrayList objects = new ArrayList();
        objects.add(company);
        objects.add(project1);
        objects.add(project2);
        objects.add(project3);

        return objects;
    }

    @Override
    protected ArrayList getRootClasses() {
        ArrayList classes = new ArrayList();
        classes.add(Company.class);
        classes.add(Project.class);
        return classes;
    }

    @Override
    protected Class<?> getSourceClass() {
        return Company.class;
    }

    @Override
    protected String getTestDocument() {
        return XML_TEST_RESOURCE;
    }

    @Override
    protected void createTables() {
        // Drop tables
        XQueryInteraction interaction = new XQueryInteraction();
        XMLFileInteractionSpec spec = new XMLFileInteractionSpec();

        interaction = new XQueryInteraction();
        interaction.setFunctionName("drop-PROJECT");
        spec = new XMLFileInteractionSpec();
        spec.setFileName("project.xml");
        spec.setInteractionType(XMLFileInteractionSpec.DELETE);
        interaction.setInteractionSpec(spec);
        session.executeNonSelectingCall(interaction);

        interaction = new XQueryInteraction();
        interaction.setFunctionName("drop-COMPANY");
        spec = new XMLFileInteractionSpec();
        spec.setFileName("company.xml");
        spec.setInteractionType(XMLFileInteractionSpec.DELETE);
        interaction.setInteractionSpec(spec);
        session.executeNonSelectingCall(interaction);
    }

    @Override
    public void testXMLDocumentToObject() throws Exception {
        updateProjectForReading();

        Vector objects = new Vector();
        for (int i = 0; i < getRootClasses().size(); i++) {
            objects.addAll(session.readAllObjects((Class)getRootClasses().get(i)));
        }

        log("**testXMLDocumentToObject**");
        log("****Expected:");
        log(getControlObject().toString());
        log("***Actual:");
        assertTrue(objects.size() == 4);
        log(objects.toString());
        assertTrue(((java.util.ArrayList)getControlObject()).size() == objects.size());
        ArrayList control = (ArrayList)getControlObject();
        assertEquals(control.get(0), objects.elementAt(0));
        Company company = (Company)objects.get(0);
                Department dept = (Department)company.getDepartments().get(0);
        Vector emps = dept.getEmployees();
        for (int i = 0; i < emps.size(); i++) {
            Employee emp = (Employee)emps.get(i);
            String name = emp.getFirstName();
            Project p = emp.getProject();
            if (name.equalsIgnoreCase(CONTROL_EMPLOYEE1_NAME)) {
                assertEquals(p.getName(), CONTROL_PROJECT3_NAME);
                assertEquals(p.getId(), CONTROL_PROJECT3_ID);
            } else if (name.equalsIgnoreCase(CONTROL_EMPLOYEE2_NAME)) {
                assertEquals(p.getName(), CONTROL_PROJECT2_NAME);
                assertEquals(p.getId(), CONTROL_PROJECT2_ID);
            }
        }
    }
}
