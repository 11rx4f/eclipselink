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
//     Oracle - initial API and implementation
//
package org.eclipse.persistence.jpa.jpql.parser;

/**
 * The abstract implementation of {@link EclipseLinkExpressionVisitor}.
 * <p>
 * Provisional API: This interface is part of an interim API that is still under development and
 * expected to change significantly before reaching stability. It is available at this early stage
 * to solicit feedback from pioneering adopters on the understanding that any code that uses this
 * API will almost certainly be broken (repeatedly) as the API evolves.
 *
 * @version 2.5
 * @since 2.4
 * @author Pascal Filion
 */
public abstract class AbstractEclipseLinkExpressionVisitor extends AbstractExpressionVisitor
                                                           implements EclipseLinkExpressionVisitor {

    /**
     * Default constructor.
     */
    protected AbstractEclipseLinkExpressionVisitor() {
    }

    @Override
    public void visit(AsOfClause expression) {
    }

    @Override
    public void visit(CastExpression expression) {
    }

    @Override
    public void visit(ConnectByClause expression) {
    }

    @Override
    public void visit(DatabaseType expression) {
    }

    @Override
    public void visit(ExtractExpression expression) {
    }

    @Override
    public void visit(HierarchicalQueryClause expression) {
    }

    @Override
    public void visit(OrderSiblingsByClause expression) {
    }

    @Override
    public void visit(RegexpExpression expression) {
    }

    @Override
    public void visit(StartWithClause expression) {
    }

    @Override
    public void visit(TableExpression expression) {
    }

    @Override
    public void visit(TableVariableDeclaration expression) {
    }

    @Override
    public void visit(UnionClause expression) {
    }
}
