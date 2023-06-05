package com.sportsmanagement.apigateway.presentationlayer.Athlete;

public class AthleteRequestModel  {

    private String teamId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String sportName;
    private Integer pointsWon;
    private Integer gamesWon;
    private Integer gamesLost;
    private Double height;
    private Double weight;
    private Integer age;
    private String gender;
    private Status status;

    public AthleteRequestModel(String teamId, String firstName, String lastName, String emailAddress, String sportName, Integer pointsWon, Integer gamesWon, Integer gamesLost, Double height, Double weight, Integer age, String gender, Status status) {
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.sportName = sportName;
        this.pointsWon = pointsWon;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.status = status;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public Integer getPointsWon() {
        return pointsWon;
    }

    public void setPointsWon(Integer pointsWon) {
        this.pointsWon = pointsWon;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Integer getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(Integer gamesLost) {
        this.gamesLost = gamesLost;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
