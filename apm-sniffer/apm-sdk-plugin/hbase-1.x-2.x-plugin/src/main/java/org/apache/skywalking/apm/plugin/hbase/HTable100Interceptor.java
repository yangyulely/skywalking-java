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

package org.apache.skywalking.apm.plugin.hbase;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.ClusterConnection;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.util.StringUtil;

public class HTable100Interceptor extends HTableInterceptor {

    @Override
    public void onConstruct(EnhancedInstance objInst, Object[] allArguments) throws Throwable {
        Configuration configuration;
        if (allArguments[1] instanceof ClusterConnection) {
            configuration = ((ClusterConnection) allArguments[1]).getConfiguration();
        } else if (allArguments[0] instanceof Configuration) {
            configuration = (Configuration) allArguments[0];
        } else {
            return;
        }

        Field field = configuration.getClass().getDeclaredField("overlay");
        field.setAccessible(true);
        Properties properties = (Properties) field.get(configuration);
        String value = properties.getProperty("hbase.zookeeper.quorum");
        if (StringUtil.isNotBlank(value)) {
            objInst.setSkyWalkingDynamicField(value);
        }
    }
}
