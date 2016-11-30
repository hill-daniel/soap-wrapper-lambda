package de.codecentric.lambda;

import de.codecentric.soap.SoapXmlMessage;

import org.junit.Before;
import org.junit.Test;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import static org.assertj.core.api.Assertions.assertThat;

public class WrapperApiEndpointIntegrationTest {

    /**
     * Provide your AWS API endpoint here
     */
    private static final String WRAPPER_API_ENDPOINT = "";
    private static final String EXPECTED_SOAP_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:codecentric=\"https://www.codecentric.de\"><SOAP-ENV:Header/><SOAP-ENV:Body><codecentric:location><codecentric:place>Berlin</codecentric:place><codecentric:position><codecentric:latitude>52.510818</codecentric:latitude><codecentric:longitude>13.372008</codecentric:longitude></codecentric:position></codecentric:location></SOAP-ENV:Body></SOAP-ENV:Envelope>";

    private SOAPConnectionFactory soapConnectionFactory;

    @Before
    public void setUp() throws Exception {
        soapConnectionFactory = SOAPConnectionFactory.newInstance();
    }

    @Test
    public void should_retrieve_soap_message_from_wrapper_endpoint() throws Exception {
        SOAPMessage request = ExampleSoapMessage.create();

        SOAPMessage response = executeApiCallWith(request);

        assertThat(new SoapXmlMessage(response).toXml()).isEqualTo(EXPECTED_SOAP_XML);
    }

    private SOAPMessage executeApiCallWith(SOAPMessage soapMessage) throws SOAPException {
        SOAPConnection connection = null;
        try {
            connection = soapConnectionFactory.createConnection();
            return connection.call(soapMessage, WRAPPER_API_ENDPOINT);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

