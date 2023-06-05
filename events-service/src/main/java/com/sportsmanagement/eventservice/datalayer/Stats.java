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
public class Stats {


    private Integer pointsWon;
    private Integer gamesWon;
    private Integer gamesLost;


}
