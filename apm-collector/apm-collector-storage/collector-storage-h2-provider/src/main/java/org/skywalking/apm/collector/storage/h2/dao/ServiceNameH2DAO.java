/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.collector.storage.h2.dao;

import java.util.HashMap;
import java.util.Map;
import org.skywalking.apm.collector.client.h2.H2Client;
import org.skywalking.apm.collector.client.h2.H2ClientException;
import org.skywalking.apm.collector.core.data.Data;
import org.skywalking.apm.collector.storage.dao.IServiceNameDAO;
import org.skywalking.apm.collector.storage.h2.base.dao.H2DAO;
import org.skywalking.apm.collector.storage.sql.SqlBuilder;
import org.skywalking.apm.collector.storage.table.register.ServiceNameDataDefine;
import org.skywalking.apm.collector.storage.table.register.ServiceNameTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peng-yongsheng, clevertension
 */
public class ServiceNameH2DAO extends H2DAO implements IServiceNameDAO {
    private final Logger logger = LoggerFactory.getLogger(ServiceNameH2DAO.class);

    @Override
    public int getMaxServiceId() {
        return getMaxId(ServiceNameTable.TABLE, ServiceNameTable.COLUMN_SERVICE_ID);
    }

    @Override
    public int getMinServiceId() {
        return getMinId(ServiceNameTable.TABLE, ServiceNameTable.COLUMN_SERVICE_ID);
    }

    @Override
    public void save(Data data) {
        String id = ServiceNameDataDefine.ServiceName.INSTANCE.getId(data);
        int applicationId = ServiceNameDataDefine.ServiceName.INSTANCE.getApplicationId(data);
        int serviceId = ServiceNameDataDefine.ServiceName.INSTANCE.getServiceId(data);
        String serviceName = ServiceNameDataDefine.ServiceName.INSTANCE.getServiceName(data);

        logger.debug("save service name register info, application id: {}, service name: {}", applicationId, serviceName);
        H2Client client = getClient();
        Map<String, Object> source = new HashMap<>();
        source.put(ServiceNameTable.COLUMN_ID, id);
        source.put(ServiceNameTable.COLUMN_SERVICE_ID, serviceId);
        source.put(ServiceNameTable.COLUMN_APPLICATION_ID, applicationId);
        source.put(ServiceNameTable.COLUMN_SERVICE_NAME, serviceName);

        String sql = SqlBuilder.buildBatchInsertSql(ServiceNameTable.TABLE, source.keySet());
        Object[] params = source.values().toArray(new Object[0]);
        try {
            client.execute(sql, params);
        } catch (H2ClientException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
