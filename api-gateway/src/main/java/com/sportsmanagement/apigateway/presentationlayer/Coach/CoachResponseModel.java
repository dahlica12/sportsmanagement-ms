package com.sportsmanagement.apigateway.presentationlayer.Coach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CoachResponseModel extends RepresentationModel<CoachResponseModel> {

    @JsonProperty("coachId")
    private final String coachId;

    @JsonProperty("teamId")
    private final String teamId;

    @JsonProperty("firstName")
    private final String firstName;

    @JsonProperty("lastName")
    private final String lastName;

    @JsonProperty("emailAddress")
    private final String emailAddress;

    @JsonProperty("phoneNumber")
    private final String phoneNumber;

    @JsonProperty("salary")
    private final Double salary;

    @JsonProperty("title")
    private final String title;
    //private final String title;
    //private final Integer yearsInPosition;

    public CoachResponseModel(String coachId, String teamId, String firstName, String lastName, String emailAddress, String phoneNumber, Double salary, String title) {
        this.coachId = coachId;
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.title = title;
    }

    public CoachResponseModel() {
        // Initialize class members
        coachId = "";
        teamId = "";
        firstName = "";
        lastName = "";
        emailAddress = "";
        phoneNumber = "";
        salary = 0.0;
        title = "";
    }

}
