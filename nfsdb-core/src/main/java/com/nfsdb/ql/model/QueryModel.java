/*******************************************************************************
 *  _  _ ___ ___     _ _
 * | \| | __/ __| __| | |__
 * | .` | _|\__ \/ _` | '_ \
 * |_|\_|_| |___/\__,_|_.__/
 *
 * Copyright (c) 2014-2015. The NFSdb project and its contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.nfsdb.ql.model;

import com.nfsdb.collections.IntHashSet;
import com.nfsdb.collections.Mutable;
import com.nfsdb.collections.ObjList;
import com.nfsdb.collections.ObjectPoolFactory;
import com.nfsdb.factory.configuration.JournalMetadata;
import com.nfsdb.ql.JournalRecordSource;
import com.nfsdb.ql.Record;

public class QueryModel implements Mutable {
    public static final QueryModelFactory FACTORY = new QueryModelFactory();

    private final ObjList<QueryColumn> columns = new ObjList<>();
    private final ObjList<JoinModel> joinModels = new ObjList<>();
    private final ObjList<String> groupBy = new ObjList<>();
    private final ObjList<ExprNode> orderBy = new ObjList<>();
    private final IntHashSet dependencies = new IntHashSet();
    private final IntHashSet parents = new IntHashSet(4);
    private ExprNode whereClause;
    private QueryModel nestedModel;
    private ExprNode journalName;
    private String alias;
    private ExprNode latestBy;
    private JournalRecordSource<? extends Record> recordSource;
    private JournalMetadata metadata;
    private int position;
    private JoinContext context;


    protected QueryModel() {
    }

    public void addColumn(QueryColumn column) {
        columns.add(column);
    }

    public void addDependency(int index) {
        dependencies.add(index);
    }

    public void addGroupBy(String name) {
        groupBy.add(name);
    }

    public void addJoinModel(JoinModel model) {
        joinModels.add(model);
    }

    public void addOrderBy(ExprNode node) {
        orderBy.add(node);
    }

    public void clear() {
        columns.clear();
        joinModels.clear();
        groupBy.clear();
        orderBy.clear();
        dependencies.clear();
        whereClause = null;
        nestedModel = null;
        journalName = null;
        alias = null;
        latestBy = null;
        recordSource = null;
        metadata = null;
        position = 0;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ObjList<QueryColumn> getColumns() {
        return columns;
    }

    public JoinContext getContext() {
        return context;
    }

    public void setContext(JoinContext context) {
        this.context = context;
    }

    public IntHashSet getDependencies() {
        return dependencies;
    }

    public ObjList<String> getGroupBy() {
        return groupBy;
    }

    public ObjList<JoinModel> getJoinModels() {
        return joinModels;
    }

    public ExprNode getJournalName() {
        return journalName;
    }

    public void setJournalName(ExprNode journalName) {
        this.journalName = journalName;
    }

    public ExprNode getLatestBy() {
        return latestBy;
    }

    public void setLatestBy(ExprNode latestBy) {
        this.latestBy = latestBy;
    }

    public JournalMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(JournalMetadata metadata) {
        this.metadata = metadata;
    }

    public QueryModel getNestedModel() {
        return nestedModel;
    }

    public void setNestedModel(QueryModel nestedModel) {
        this.nestedModel = nestedModel;
    }

    public ObjList<ExprNode> getOrderBy() {
        return orderBy;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public JournalRecordSource<? extends Record> getRecordSource() {
        return recordSource;
    }

    public void setRecordSource(JournalRecordSource<? extends Record> recordSource) {
        this.recordSource = recordSource;
    }

    public ExprNode getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(ExprNode whereClause) {
        this.whereClause = whereClause;
    }

    public static final class QueryModelFactory implements ObjectPoolFactory<QueryModel> {
        @Override
        public QueryModel newInstance() {
            return new QueryModel();
        }
    }
}
