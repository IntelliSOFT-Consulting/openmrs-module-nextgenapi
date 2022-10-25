package org.openmrs.module.basicmodule.web.controller.mailing.emailcontroller;

public class DbResults {

    private String details;

    public DbResults(String details) {
        this.details = details;
    }

    public DbResults() {
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
