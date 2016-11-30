package de.codecentric.soap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import static com.google.common.base.Charsets.UTF_8;

public class SoapXmlMessage extends SOAPMessage {

    private final SOAPMessage delegateSoapMessage;

    public SoapXmlMessage(SOAPMessage delegateSoapMessage) {
        this.delegateSoapMessage = delegateSoapMessage;
    }

    @Override
    public void setContentDescription(String description) {
        delegateSoapMessage.setContentDescription(description);
    }

    @Override
    public String getContentDescription() {
        return delegateSoapMessage.getContentDescription();
    }

    @Override
    public SOAPPart getSOAPPart() {
        return delegateSoapMessage.getSOAPPart();
    }

    @Override
    public void removeAllAttachments() {
        delegateSoapMessage.removeAllAttachments();
    }

    @Override
    public int countAttachments() {
        return delegateSoapMessage.countAttachments();
    }

    @Override
    public Iterator getAttachments() {
        return delegateSoapMessage.getAttachments();
    }

    @Override
    public Iterator getAttachments(MimeHeaders headers) {
        return delegateSoapMessage.getAttachments(headers);
    }

    @Override
    public void removeAttachments(MimeHeaders headers) {
        delegateSoapMessage.removeAttachments(headers);
    }

    @Override
    public AttachmentPart getAttachment(SOAPElement element) throws SOAPException {
        return delegateSoapMessage.getAttachment(element);
    }

    @Override
    public void addAttachmentPart(AttachmentPart AttachmentPart) {
        delegateSoapMessage.addAttachmentPart(AttachmentPart);
    }

    @Override
    public AttachmentPart createAttachmentPart() {
        return delegateSoapMessage.createAttachmentPart();
    }

    @Override
    public MimeHeaders getMimeHeaders() {
        return delegateSoapMessage.getMimeHeaders();
    }

    @Override
    public void saveChanges() throws SOAPException {
        delegateSoapMessage.saveChanges();
    }

    @Override
    public boolean saveRequired() {
        return delegateSoapMessage.saveRequired();
    }

    @Override
    public void writeTo(OutputStream out) throws SOAPException, IOException {
        delegateSoapMessage.writeTo(out);
    }

    public String toXml() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            delegateSoapMessage.writeTo(byteArrayOutputStream);
            return byteArrayOutputStream.toString(UTF_8.name());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert SOAPMessage to String", e);
        }
    }
}
