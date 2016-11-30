package de.codecentric.soap;

import java.util.Objects;

public class SoapWrapper {

    private String body;

    public SoapWrapper() {

    }

    public SoapWrapper(String body) {
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoapWrapper)) return false;
        SoapWrapper wrapper = (SoapWrapper) o;
        return Objects.equals(body, wrapper.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    @Override
    public String toString() {
        return "SoapWrapper{" +
                "body='" + body + '\'' +
                '}';
    }
}
