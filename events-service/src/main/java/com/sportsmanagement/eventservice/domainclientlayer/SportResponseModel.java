package com.sportsmanagement.eventservice.domainclientlayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportResponseModel {

    String sportId;
    String name;
}
