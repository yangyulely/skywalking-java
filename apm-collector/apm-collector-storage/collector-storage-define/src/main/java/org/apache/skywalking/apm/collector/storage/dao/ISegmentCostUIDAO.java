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


package org.apache.skywalking.apm.collector.storage.dao;

import com.google.gson.JsonObject;
import java.util.List;
import org.apache.skywalking.apm.collector.storage.base.dao.DAO;

/**
 * @author peng-yongsheng
 */
public interface ISegmentCostUIDAO extends DAO {
    JsonObject loadTop(long startTime, long endTime, long minCost, long maxCost, String operationName,
        Error error, int applicationId, List<String> segmentIds, int limit, int from, Sort sort);

    enum Sort {
        Cost, Time
    }

    enum Error {
        All, True, False
    }
}
