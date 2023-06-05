package com.sportsmanagement.eventservice.domainclientlayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SportRequestModel {



    String name;

//    public SportRequestModel() {
//    }
//
//    public SportRequestModel(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
