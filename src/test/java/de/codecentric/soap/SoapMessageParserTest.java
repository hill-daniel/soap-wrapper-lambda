package de.codecentric.soap;

import org.junit.Test;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;

public class SoapMessageParserTest {

    @Test
    public void should_throw_illegal_argument_if_given_string_is_null_or_empty() {
        SoapMessageParser soapMessageParser = new SoapMessageParser(mock(MessageFactory.class));

        assertThatExceptionOfType(IllegalArgumentException.class).
                isThrownBy(() -> soapMessageParser.parseFrom(""));
        assertThatExceptionOfType(IllegalArgumentException.class).
                isThrownBy(() -> soapMessageParser.parseFrom(null));
    }

    @Test
    public void should_parse_soap_message_from_xml() throws Exception {
        SoapMessageParser soapMessageParser = new SoapMessageParser(MessageFactory.newInstance());
        String messageAsXml = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:codecentric=\"https://www.codecentric.de\"><SOAP-ENV:Header/><SOAP-ENV:Body><codecentric:location><codecentric:place>Berlin</codecentric:place><codecentric:position><codecentric:latitude>52.510818</codecentric:latitude><codecentric:longitude>13.372008</codecentric:longitude></codecentric:position></codecentric:location></SOAP-ENV:Body></SOAP-ENV:Envelope>";

        SOAPMessage soapMessage = soapMessageParser.parseFrom(messageAsXml);

        assertThat(soapMessage).isNotNull();
    }
}