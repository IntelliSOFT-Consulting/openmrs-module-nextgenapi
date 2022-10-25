package org.openmrs.module.basicmodule.web.controller.mailing.emailcontroller;

public class ResultsResponse {

    private int code;
    private String details;


    public ResultsResponse(int code, String details) {
        this.code = code;
        this.details = details;
    }

    public ResultsResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
