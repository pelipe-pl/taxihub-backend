package com.herokuapp.backend.email;


public class Email {
    private String to;
    private String subject;
    private String content;
    private String provider;

    public Email() {
    }

    public Email(String to, String subject, String content, String provider) {
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.provider = provider;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
