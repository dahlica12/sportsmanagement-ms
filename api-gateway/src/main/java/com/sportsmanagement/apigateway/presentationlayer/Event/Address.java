package com.sportsmanagement.apigateway.presentationlayer.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class
Address {

    private String streetAddress;
    private String city;
    private String province;
    private String country;
    private String postalCode;
}


