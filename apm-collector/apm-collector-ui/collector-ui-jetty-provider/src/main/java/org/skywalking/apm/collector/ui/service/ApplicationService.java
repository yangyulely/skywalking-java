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

package org.skywalking.apm.collector.ui.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.skywalking.apm.collector.cache.ApplicationCache;
import org.skywalking.apm.collector.storage.dao.IInstanceUIDAO;
import org.skywalking.apm.collector.storage.service.DAOService;

/**
 * @author peng-yongsheng
 */
public class ApplicationService {

    private final DAOService daoService;

    public ApplicationService(DAOService daoService) {
        this.daoService = daoService;
    }

    public JsonArray getApplications(long startTime, long endTime) {
        IInstanceUIDAO instanceDAO = (IInstanceUIDAO)daoService.get(IInstanceUIDAO.class);
        JsonArray applications = instanceDAO.getApplications(startTime, endTime);

        applications.forEach(jsonElement -> {
            JsonObject application = jsonElement.getAsJsonObject();
            int applicationId = application.get("applicationId").getAsInt();
            String applicationCode = ApplicationCache.get(applicationId);
            application.addProperty("applicationCode", applicationCode);
        });
        return applications;
    }
}
