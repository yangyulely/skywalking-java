/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.collector.storage.table.service;

import org.apache.skywalking.apm.collector.core.data.Column;
import org.apache.skywalking.apm.collector.core.data.AbstractData;
import org.apache.skywalking.apm.collector.core.data.operator.AddOperation;
import org.apache.skywalking.apm.collector.core.data.operator.NonOperation;
import org.apache.skywalking.apm.collector.storage.table.Metric;

/**
 * @author peng-yongsheng
 */
public class ServiceReferenceMetric extends AbstractData implements Metric {

    private static final Column[] STRING_COLUMNS = {
        new Column(ServiceReferenceMetricTable.COLUMN_ID, new NonOperation()),
    };

    private static final Column[] LONG_COLUMNS = {
        new Column(ServiceReferenceMetricTable.COLUMN_TIME_BUCKET, new NonOperation()),

        new Column(ServiceReferenceMetricTable.COLUMN_TRANSACTION_CALLS, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_TRANSACTION_ERROR_CALLS, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_TRANSACTION_DURATION_SUM, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_TRANSACTION_ERROR_DURATION_SUM, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_CALLS, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_ERROR_CALLS, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_DURATION_SUM, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_ERROR_DURATION_SUM, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_MQ_TRANSACTION_CALLS, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_MQ_TRANSACTION_ERROR_CALLS, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_MQ_TRANSACTION_DURATION_SUM, new AddOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_MQ_TRANSACTION_ERROR_DURATION_SUM, new AddOperation()),
    };

    private static final Column[] DOUBLE_COLUMNS = {};

    private static final Column[] INTEGER_COLUMNS = {
        new Column(ServiceReferenceMetricTable.COLUMN_SOURCE_VALUE, new NonOperation()),

        new Column(ServiceReferenceMetricTable.COLUMN_FRONT_SERVICE_ID, new NonOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_BEHIND_SERVICE_ID, new NonOperation()),

        new Column(ServiceReferenceMetricTable.COLUMN_FRONT_INSTANCE_ID, new NonOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_BEHIND_INSTANCE_ID, new NonOperation()),

        new Column(ServiceReferenceMetricTable.COLUMN_FRONT_APPLICATION_ID, new NonOperation()),
        new Column(ServiceReferenceMetricTable.COLUMN_BEHIND_APPLICATION_ID, new NonOperation()),
    };

    private static final Column[] BOOLEAN_COLUMNS = {};

    private static final Column[] BYTE_COLUMNS = {};

    public ServiceReferenceMetric(String id) {
        super(id, STRING_COLUMNS, LONG_COLUMNS, DOUBLE_COLUMNS, INTEGER_COLUMNS, BOOLEAN_COLUMNS, BYTE_COLUMNS);
    }

    @Override
    public Integer getSourceValue() {
        return getDataInteger(0);
    }

    @Override
    public void setSourceValue(Integer sourceValue) {
        setDataInteger(0, sourceValue);
    }

    public Integer getFrontServiceId() {
        return getDataInteger(1);
    }

    public void setFrontServiceId(Integer frontServiceId) {
        setDataInteger(1, frontServiceId);
    }

    public Integer getBehindServiceId() {
        return getDataInteger(2);
    }

    public void setBehindServiceId(Integer behindServiceId) {
        setDataInteger(2, behindServiceId);
    }

    public Integer getFrontInstanceId() {
        return getDataInteger(3);
    }

    public void setFrontInstanceId(Integer frontInstanceId) {
        setDataInteger(3, frontInstanceId);
    }

    public Integer getBehindInstanceId() {
        return getDataInteger(4);
    }

    public void setBehindInstanceId(Integer behindInstanceId) {
        setDataInteger(4, behindInstanceId);
    }

    public Integer getFrontApplicationId() {
        return getDataInteger(5);
    }

    public void setFrontApplicationId(Integer frontApplicationId) {
        setDataInteger(5, frontApplicationId);
    }

    public Integer getBehindApplicationId() {
        return getDataInteger(6);
    }

    public void setBehindApplicationId(Integer behindApplicationId) {
        setDataInteger(6, behindApplicationId);
    }

    @Override
    public Long getTimeBucket() {
        return getDataLong(0);
    }

    @Override
    public void setTimeBucket(Long timeBucket) {
        setDataLong(0, timeBucket);
    }

    @Override
    public Long getTransactionCalls() {
        return getDataLong(1);
    }

    @Override
    public void setTransactionCalls(Long transactionCalls) {
        setDataLong(1, transactionCalls);
    }

    @Override
    public Long getTransactionErrorCalls() {
        return getDataLong(2);
    }

    @Override
    public void setTransactionErrorCalls(Long transactionErrorCalls) {
        setDataLong(2, transactionErrorCalls);
    }

    @Override
    public Long getTransactionDurationSum() {
        return getDataLong(3);
    }

    @Override
    public void setTransactionDurationSum(Long transactionDurationSum) {
        setDataLong(3, transactionDurationSum);
    }

    @Override
    public Long getTransactionErrorDurationSum() {
        return getDataLong(4);
    }

    @Override
    public void setTransactionErrorDurationSum(Long transactionErrorDurationSum) {
        setDataLong(4, transactionErrorDurationSum);
    }

    @Override
    public Long getBusinessTransactionCalls() {
        return getDataLong(5);
    }

    @Override
    public void setBusinessTransactionCalls(Long businessTransactionCalls) {
        setDataLong(5, businessTransactionCalls);
    }

    @Override
    public Long getBusinessTransactionErrorCalls() {
        return getDataLong(6);
    }

    @Override
    public void setBusinessTransactionErrorCalls(Long businessTransactionErrorCalls) {
        setDataLong(6, businessTransactionErrorCalls);
    }

    @Override
    public Long getBusinessTransactionDurationSum() {
        return getDataLong(7);
    }

    @Override
    public void setBusinessTransactionDurationSum(Long businessTransactionDurationSum) {
        setDataLong(7, businessTransactionDurationSum);
    }

    @Override
    public Long getBusinessTransactionErrorDurationSum() {
        return getDataLong(8);
    }

    @Override
    public void setBusinessTransactionErrorDurationSum(Long businessTransactionErrorDurationSum) {
        setDataLong(8, businessTransactionErrorDurationSum);
    }

    @Override
    public Long getMqTransactionCalls() {
        return getDataLong(9);
    }

    @Override
    public void setMqTransactionCalls(Long mqTransactionCalls) {
        setDataLong(9, mqTransactionCalls);
    }

    @Override
    public Long getMqTransactionErrorCalls() {
        return getDataLong(10);
    }

    @Override
    public void setMqTransactionErrorCalls(Long mqTransactionErrorCalls) {
        setDataLong(10, mqTransactionErrorCalls);
    }

    @Override
    public Long getMqTransactionDurationSum() {
        return getDataLong(11);
    }

    @Override
    public void setMqTransactionDurationSum(Long mqTransactionDurationSum) {
        setDataLong(11, mqTransactionDurationSum);
    }

    @Override
    public Long getMqTransactionErrorDurationSum() {
        return getDataLong(12);
    }

    @Override
    public void setMqTransactionErrorDurationSum(Long mqTransactionErrorDurationSum) {
        setDataLong(12, mqTransactionErrorDurationSum);
    }
}
