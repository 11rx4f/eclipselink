/*
 * Copyright (c) 2011, 2021 Oracle and/or its affiliates. All rights reserved.
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
// dmccann - March 24/2010 - 2.1 - Initial implementation
package org.eclipse.persistence.testing.jaxb.externalizedmetadata.mappings.collectionreference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;

public class CollectionReferenceReadOnlyTestCases extends JAXBWithJSONTestCases{
    private static final String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/mappings/collectionreference/root.xml";
    private static final String XML_WRITE_RESOURCE = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/mappings/collectionreference/marshal-read-only.xml";

    private static final String JSON_RESOURCE = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/mappings/collectionreference/root.json";
    private static final String JSON_WRITE_RESOURCE = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/mappings/collectionreference/marshal-read-only.json";

    private static final String ADD_ID1 = "a100";
    private static final String ADD_ID2 = "a101";
    private static final String ADD_ID3 = "a102";

    public CollectionReferenceReadOnlyTestCases(String name) throws Exception {
        super(name);
        setClasses(new Class<?>[] { Root.class });
        setControlDocument(XML_RESOURCE);
        setWriteControlDocument(XML_WRITE_RESOURCE);
        setControlJSON(JSON_RESOURCE);
        setWriteControlJSON(JSON_WRITE_RESOURCE);
    }

    /**
     * Create the control Root.
     */
     @Override
     public Object getControlObject() {
            Root root = new Root();
            List<Employee> emps = new ArrayList<Employee>();
            List<Address> adds = new ArrayList<Address>();
            List<Address> workAddresses = new ArrayList<Address>();

            Address wAddress1 = new Address();
            wAddress1.id = ADD_ID1;
            adds.add(wAddress1);
            workAddresses.add(wAddress1);

            Address wAddress2 = new Address();
            wAddress2.id = ADD_ID2;
            adds.add(wAddress2);
            workAddresses.add(wAddress2);

            Address wAddress3 = new Address();
            wAddress3.id = ADD_ID3;
            adds.add(wAddress3);

            Employee ctrlEmp = new Employee();
            ctrlEmp.workAddresses = workAddresses;
            emps.add(ctrlEmp);

            root.addresses = adds;
            root.employees = emps;

            return root;

        }

    @Override
    public Map getProperties(){
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("org/eclipse/persistence/testing/jaxb/externalizedmetadata/mappings/collectionreference/read-only-oxm.xml");

            HashMap<String, Source> metadataSourceMap = new HashMap<String, Source>();
            metadataSourceMap.put("org.eclipse.persistence.testing.jaxb.externalizedmetadata.mappings.collectionreference", new StreamSource(inputStream));
            Map<String, Map<String, Source>> properties = new HashMap<String, Map<String, Source>>();
            properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, metadataSourceMap);

            return properties;
        }


        public void testSchemaGen() throws Exception{
               List controlSchemas = new ArrayList();
               InputStream is = ClassLoader.getSystemResourceAsStream("org/eclipse/persistence/testing/jaxb/externalizedmetadata/mappings/collectionreference/root.xsd");

               controlSchemas.add(is);

               super.testSchemaGen(controlSchemas);
        }


        @Override
        public void testRoundTrip(){
            //not applicable since read and write docs are different
        }
}
