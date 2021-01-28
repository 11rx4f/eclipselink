/*******************************************************************************
 * Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.persistence.jpa.test.returninsert.model;

import org.eclipse.persistence.annotations.ReturnInsert;
import org.eclipse.persistence.annotations.ReturnUpdate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * ReturnInsertDetailEmbeddedEmbedded instance to be {@link Embedded} into other embedded entities.
 */
@Embeddable
public class ReturnInsertDetailEmbeddedEmbedded implements Serializable {

	private static final long serialVersionUID = 1712864132706065027L;


	@Column(name = "COL5")
	private String col5;

	@ReturnInsert(returnOnly = true)
	@Column(name = "COL5_VIRTUAL")
	private String col5Virtual;

	public ReturnInsertDetailEmbeddedEmbedded() {
	}

	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getCol5Virtual() {
		return col5Virtual;
	}

	public void setCol5Virtual(String col5Virtual) {
		this.col5Virtual = col5Virtual;
	}
}