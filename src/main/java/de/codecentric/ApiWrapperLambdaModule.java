package de.codecentric;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;

public class ApiWrapperLambdaModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    SOAPConnectionFactory soapConnectionFactory() {
        try {
            return SOAPConnectionFactory.newInstance();
        } catch (SOAPException e) {
            throw new RuntimeException("Failed to create SOAPConnectionFactory", e);
        }
    }

    @Provides
    @Singleton
    MessageFactory messageFactory() {
        try {
            return MessageFactory.newInstance();
        } catch (SOAPException e) {
            throw new RuntimeException("Faield to create MessageFactory", e);
        }
    }
}
