--
-- Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
--
-- This program and the accompanying materials are made available under the
-- terms of the Eclipse Public License v. 2.0 which is available at
-- http://www.eclipse.org/legal/epl-2.0,
-- or the Eclipse Distribution License v. 1.0 which is available at
-- http://www.eclipse.org/org/documents/edl-v10.php.
--
-- SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
--

CREATE TABLE XR_KEYMAP_ADDRESS (
    ADDRESS_ID NUMERIC(15) NOT NULL,
    STREET VARCHAR(80),
    CITY VARCHAR(80),
    PROVINCE VARCHAR(80),
    PRIMARY KEY (ADDRESS_ID)
)|
INSERT INTO XR_KEYMAP_ADDRESS (ADDRESS_ID, STREET, CITY, PROVINCE) VALUES (1, '20 Pinetrail Cres.', 'Nepean', 'Ont')|
INSERT INTO XR_KEYMAP_ADDRESS (ADDRESS_ID, STREET, CITY, PROVINCE) VALUES (2, 'Davis Side Rd.', 'Carleton Place', 'Ont')|
CREATE TABLE XR_KEYMAP_EMPLOYEE (
    EMP_ID NUMERIC(15) NOT NULL,
    F_NAME VARCHAR(40),
    L_NAME VARCHAR(40),
    ADDR_ID NUMERIC(15),
    PRIMARY KEY (EMP_ID),
    CONSTRAINT XR_KEYMAP_EMPLOYEE_ADDRESS FOREIGN KEY (ADDR_ID) REFERENCES XR_KEYMAP_ADDRESS (ADDRESS_ID)
)|
INSERT INTO XR_KEYMAP_EMPLOYEE (EMP_ID, F_NAME, L_NAME, ADDR_ID) VALUES (1, 'Mike', 'Norman', 1)|
INSERT INTO XR_KEYMAP_EMPLOYEE (EMP_ID, F_NAME, L_NAME, ADDR_ID) VALUES (2, 'Rick', 'Barkhouse', 2)|
CREATE TABLE XR_KEYMAP_PHONE (
    PHONE_ID NUMERIC(15) NOT NULL,
    OWNER_ID NUMERIC(15) NOT NULL,
    AREA_CODE VARCHAR(3),
    P_NUMBER VARCHAR(7),
    TYPE VARCHAR(15) NOT NULL,
    PRIMARY KEY (PHONE_ID),
    CONSTRAINT XR_KEYMAP_PHONE_EMPLOYEE FOREIGN KEY (OWNER_ID) REFERENCES XR_KEYMAP_EMPLOYEE (EMP_ID)
)|
INSERT INTO XR_KEYMAP_PHONE (PHONE_ID, OWNER_ID, AREA_CODE, P_NUMBER, TYPE) VALUES (1, 1, '613', '2281808', 'Home')|
INSERT INTO XR_KEYMAP_PHONE (PHONE_ID, OWNER_ID, AREA_CODE, P_NUMBER, TYPE) VALUES (2, 1, '613', '2884638', 'Work')|
INSERT INTO XR_KEYMAP_PHONE (PHONE_ID, OWNER_ID, AREA_CODE, P_NUMBER, TYPE) VALUES (3, 2, '613', '2832684', 'Home')|
INSERT INTO XR_KEYMAP_PHONE (PHONE_ID, OWNER_ID, AREA_CODE, P_NUMBER, TYPE) VALUES (4, 2, '613', '2884613', 'Work')|