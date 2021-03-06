package com.ara.game.usecases.battleship.point.dto;

final public class PointCreateStringDto {

    private final String pointString;

    private PointCreateStringDto(Builder builder) {
        this.pointString = builder.pointString;
    }

    public String getPointString() {
        return pointString;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String pointString;

        public Builder withPointString(final String pointString) {
            this.pointString = pointString;
            return this;
        }

        public PointCreateStringDto build() {
            return new PointCreateStringDto(this);
        }
    }
}
