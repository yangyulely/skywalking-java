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

package org.apache.skywalking.apm.collector.agent.grpc.provider.handler.mock;

import io.grpc.ManagedChannel;
import java.util.UUID;
import org.apache.skywalking.apm.network.proto.Application;
import org.apache.skywalking.apm.network.proto.ApplicationInstance;
import org.apache.skywalking.apm.network.proto.ApplicationMapping;
import org.apache.skywalking.apm.network.proto.ApplicationRegisterServiceGrpc;
import org.apache.skywalking.apm.network.proto.InstanceDiscoveryServiceGrpc;
import org.apache.skywalking.apm.network.proto.OSInfo;
import org.apache.skywalking.apm.network.proto.ServiceNameCollection;
import org.apache.skywalking.apm.network.proto.ServiceNameDiscoveryServiceGrpc;
import org.apache.skywalking.apm.network.proto.ServiceNameElement;
import org.joda.time.DateTime;

/**
 * @author peng-yongsheng
 */
class RegisterMock {

    private ApplicationRegisterServiceGrpc.ApplicationRegisterServiceBlockingStub applicationRegisterServiceBlockingStub;
    private InstanceDiscoveryServiceGrpc.InstanceDiscoveryServiceBlockingStub instanceDiscoveryServiceBlockingStub;
    private ServiceNameDiscoveryServiceGrpc.ServiceNameDiscoveryServiceBlockingStub serviceNameDiscoveryServiceBlockingStub;

    void mock(ManagedChannel channel) {
        applicationRegisterServiceBlockingStub = ApplicationRegisterServiceGrpc.newBlockingStub(channel);
        instanceDiscoveryServiceBlockingStub = InstanceDiscoveryServiceGrpc.newBlockingStub(channel);
        serviceNameDiscoveryServiceBlockingStub = ServiceNameDiscoveryServiceGrpc.newBlockingStub(channel);
        registerConsumer();
        registerProvider();
    }

    private void registerConsumer() {
        Application.Builder application = Application.newBuilder();
        application.setApplicationCode("dubbox-consumer");
        ApplicationMapping applicationMapping = applicationRegisterServiceBlockingStub.applicationCodeRegister(application.build());

        ApplicationInstance.Builder instance = ApplicationInstance.newBuilder();
        instance.setApplicationId(applicationMapping.getApplication().getValue());
        instance.setAgentUUID(UUID.randomUUID().toString());
        instance.setRegisterTime(new DateTime("2017-01-01T00:01:01.001").getMillis());

        OSInfo.Builder osInfo = OSInfo.newBuilder();
        osInfo.setHostname("pengys");
        osInfo.setOsName("MacOS XX");
        osInfo.setProcessNo(1001);
        osInfo.addIpv4S("10.0.0.3");
        osInfo.addIpv4S("10.0.0.4");
        instance.setOsinfo(osInfo);
        instanceDiscoveryServiceBlockingStub.registerInstance(instance.build());

        ServiceNameCollection.Builder serviceNameCollection = ServiceNameCollection.newBuilder();
        ServiceNameElement.Builder serviceNameElement = ServiceNameElement.newBuilder();
        serviceNameElement.setApplicationId(applicationMapping.getApplication().getValue());
        serviceNameElement.setServiceName("org.skywaking.apm.testcase.dubbo.services.GreetService.doBusiness()");
        serviceNameCollection.addElements(serviceNameElement);
        serviceNameDiscoveryServiceBlockingStub.discovery(serviceNameCollection.build());
    }

    private void registerProvider() {
        Application.Builder application = Application.newBuilder();
        application.setApplicationCode("dubbox-provider");
        ApplicationMapping applicationMapping = applicationRegisterServiceBlockingStub.applicationCodeRegister(application.build());

        ApplicationInstance.Builder instance = ApplicationInstance.newBuilder();
        instance.setApplicationId(applicationMapping.getApplication().getValue());
        instance.setAgentUUID(UUID.randomUUID().toString());
        instance.setRegisterTime(new DateTime("2017-01-01T00:01:01.001").getMillis());

        OSInfo.Builder osInfo = OSInfo.newBuilder();
        osInfo.setHostname("peng-yongsheng");
        osInfo.setOsName("MacOS X");
        osInfo.setProcessNo(1000);
        osInfo.addIpv4S("10.0.0.1");
        osInfo.addIpv4S("10.0.0.2");
        instance.setOsinfo(osInfo);
        instanceDiscoveryServiceBlockingStub.registerInstance(instance.build());

        ServiceNameCollection.Builder serviceNameCollection = ServiceNameCollection.newBuilder();
        ServiceNameElement.Builder serviceNameElement = ServiceNameElement.newBuilder();
        serviceNameElement.setApplicationId(applicationMapping.getApplication().getValue());
        serviceNameElement.setServiceName("org.skywaking.apm.testcase.dubbo.services.GreetService.doBusiness()");
        serviceNameCollection.addElements(serviceNameElement);
        serviceNameDiscoveryServiceBlockingStub.discovery(serviceNameCollection.build());
    }
}
