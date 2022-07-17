package com.example.splitmycostsapi.models.requestmodels;

public class AddContributorRequest {

    public Long contributorId;

    public AddContributorRequest() {
    }

    public AddContributorRequest(Long contributorId) {
        this.contributorId = contributorId;
    }

    public Long getContributorId() {
        return contributorId;
    }

    public void setContributorId(Long contributorId) {
        this.contributorId = contributorId;
    }
}
