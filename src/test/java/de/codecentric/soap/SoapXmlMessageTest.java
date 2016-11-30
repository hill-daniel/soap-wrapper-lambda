package de.codecentric.soap;


import de.codecentric.lambda.ExampleSoapMessage;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SoapXmlMessageTest {

    @Test
    public void should_create_xml_from_soap_message() throws Exception {
        SoapXmlMessage soapMessage = new SoapXmlMessage(ExampleSoapMessage.create());
        String expectedXml = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:codecentric=\"https://www.codecentric.de\"><SOAP-ENV:Header/><SOAP-ENV:Body><codecentric:location><codecentric:place>Berlin</codecentric:place></codecentric:location></SOAP-ENV:Body></SOAP-ENV:Envelope>";

        String messageAsXml = soapMessage.toXml();

        assertThat(messageAsXml).isEqualTo(expectedXml);
    }
}