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

package org.apache.skywalking.apm.agent.core.context;

import java.util.Optional;

/**
 * Inject or read the extension protocol fields,such as {@link ExtensionContext#sendingTimestamp}.
 */
public class ExtensionInjector {

    private final ExtensionContext extensionContext;

    ExtensionInjector(final ExtensionContext extensionContext) {
        this.extensionContext = extensionContext;
    }

    /**
     * Inject the current time in milliseconds to the {@link ExtensionContext}.
     */
    public void injectSendingTimestamp() {
        extensionContext.setSendingTimestamp(System.currentTimeMillis());
    }

    /**
     * Read the exit span sending timestamp from the {@link ExtensionContext}.
     */
    public Optional<Long> readSendingTimestamp() {
        return Optional.ofNullable(extensionContext.getSendingTimestamp());
    }
}
