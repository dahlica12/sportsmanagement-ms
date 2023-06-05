package com.sportsmanagement.apigateway.presentationlayer.SportsTeam;

public class SportRequestModel {



    String name;

    public SportRequestModel() {
    }

    public SportRequestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
