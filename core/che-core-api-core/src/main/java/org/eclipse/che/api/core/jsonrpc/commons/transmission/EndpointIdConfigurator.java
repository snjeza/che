/*******************************************************************************
 * Copyright (c) 2012-2017 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.api.core.jsonrpc.commons.transmission;

import org.eclipse.che.api.core.jsonrpc.commons.JsonRpcErrorTransmitter;
import org.eclipse.che.api.core.jsonrpc.commons.JsonRpcMarshaller;
import org.eclipse.che.api.core.jsonrpc.commons.ResponseDispatcher;
import org.eclipse.che.api.core.websocket.commons.WebSocketMessageTransmitter;
import org.slf4j.Logger;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Endpoint ID configurator to defined endpoint id that the request
 * should be addressed to.
 */
public class EndpointIdConfigurator {
    private final static Logger LOGGER = getLogger(EndpointIdConfigurator.class);

    private final JsonRpcMarshaller           marshaller;
    private final ResponseDispatcher          dispatcher;
    private final WebSocketMessageTransmitter transmitter;

    @Inject
    EndpointIdConfigurator(JsonRpcMarshaller marshaller, ResponseDispatcher dispatcher, WebSocketMessageTransmitter transmitter) {
        this.marshaller = marshaller;
        this.dispatcher = dispatcher;
        this.transmitter = transmitter;
    }

    public MethodNameConfigurator endpointId(String id) {
        checkNotNull(id, "Endpoint ID must not be null");
        checkArgument(!id.isEmpty(), "Endpoint ID must not be empty");

        LOGGER.debug("Configuring outgoing request endpoint ID: " + id);

        return new MethodNameConfigurator(marshaller, dispatcher, transmitter, id);
    }
}
