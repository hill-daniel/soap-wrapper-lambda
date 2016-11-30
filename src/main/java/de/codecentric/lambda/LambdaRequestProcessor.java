package de.codecentric.lambda;

import com.google.inject.Inject;

import de.codecentric.soap.ExampleResponse;
import de.codecentric.soap.SoapXmlMessage;
import de.codecentric.soap.SoapMessageParser;
import de.codecentric.soap.SoapWrapper;

import org.apache.log4j.Logger;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

class LambdaRequestProcessor {

    private static final Logger LOGGER = Logger.getLogger(LambdaRequestProcessor.class);

    private final SoapMessageParser soapMessageParser;

    @Inject
    LambdaRequestProcessor(SoapMessageParser soapMessageParser) {
        this.soapMessageParser = soapMessageParser;
    }

    SoapWrapper process(SoapWrapper request) {
        LOGGER.info("Received api request: [" + request + "]");
        try {
            SOAPMessage soapMessage = soapMessageParser.parseFrom(request.getBody());
            SOAPMessage response = handle(soapMessage);
            return wrap(response);
        } catch (Exception e) {
            LOGGER.error("Failed to handle API request: [" + request + "]", e);
        }
        return null;
    }

    private SOAPMessage handle(SOAPMessage soapMessage) {
        try {
            // TODO actually handle the message. For demonstration purposes return example response.
            return ExampleResponse.create();
        } catch (SOAPException e) {
            throw new RuntimeException("Failed to create SOAPMessage", e);
        }
    }

    private SoapWrapper wrap(SOAPMessage soapMessage) {
        String messageAsXml = new SoapXmlMessage(soapMessage).toXml();
        return new SoapWrapper(messageAsXml);
    }
}
