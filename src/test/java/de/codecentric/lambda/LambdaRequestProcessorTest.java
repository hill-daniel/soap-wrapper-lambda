package de.codecentric.lambda;

import de.codecentric.soap.SoapMessageParser;
import de.codecentric.soap.SoapWrapper;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class LambdaRequestProcessorTest {

    private static final String EXPECTED_SOAP_RESPONSE_XML = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:codecentric=\"https://www.codecentric.de\"><SOAP-ENV:Header/><SOAP-ENV:Body><codecentric:location><codecentric:place>Berlin</codecentric:place><codecentric:position><codecentric:latitude>52.510818</codecentric:latitude><codecentric:longitude>13.372008</codecentric:longitude></codecentric:position></codecentric:location></SOAP-ENV:Body></SOAP-ENV:Envelope>";

    private SoapMessageParser soapMessageParser;
    private LambdaRequestProcessor lambdaRequestProcessor;

    @Before
    public void setUp() throws Exception {
        soapMessageParser = mock(SoapMessageParser.class);
        lambdaRequestProcessor = new LambdaRequestProcessor(soapMessageParser);
    }

    @Test
    public void should_return_soap_xml_with_location_of_cc_berlin() throws Exception {
        SoapWrapper wrappedResponse = lambdaRequestProcessor.process(new SoapWrapper(""));

        assertThat(wrappedResponse.getBody()).isEqualTo(EXPECTED_SOAP_RESPONSE_XML);
    }

    @Test
    public void should_parse_incoming_wrapper_body() throws Exception {
        SoapWrapper requestWrapper = new SoapWrapper("body to parse");

        lambdaRequestProcessor.process(requestWrapper);

        verify(soapMessageParser).parseFrom("body to parse");
    }
}