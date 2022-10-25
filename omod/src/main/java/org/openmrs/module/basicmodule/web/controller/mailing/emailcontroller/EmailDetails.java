package org.openmrs.module.basicmodule.web.controller.mailing.emailcontroller;


import org.springframework.web.multipart.MultipartFile;

public class EmailDetails {

    private String receiver;
    private String subject;
    private String message;
    private MultipartFile file;

    public EmailDetails() {
    }

    public EmailDetails(String receiver, String subject, String message) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}