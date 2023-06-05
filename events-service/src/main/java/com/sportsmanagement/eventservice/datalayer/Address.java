package com.sportsmanagement.eventservice.datalayer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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


