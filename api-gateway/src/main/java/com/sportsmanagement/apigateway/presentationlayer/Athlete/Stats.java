package com.sportsmanagement.apigateway.presentationlayer.Athlete;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;


public class Stats {


    private Integer pointsWon;
    private Integer gamesWon;
    private Integer gamesLost;

    @SuppressWarnings("unused")
    public Stats() {
    }

    public Stats(@NotNull Integer pointsWon, @NotNull Integer gamesWon, @NotNull Integer gamesLost) {

        Objects.requireNonNull(this.pointsWon = pointsWon);
        Objects.requireNonNull(this.gamesWon = gamesWon);
        Objects.requireNonNull(this.gamesLost = gamesLost);
    }

    public @NotNull Integer getPointsWon() {
        return pointsWon;
    }

    public @NotNull Integer getGamesWon() {
        return gamesWon;
    }

    public @NotNull Integer getGamesLost() {
        return gamesLost;
    }
}
