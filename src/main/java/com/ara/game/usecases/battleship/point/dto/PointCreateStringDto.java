package com.ara.game.usecases.battleship.point.dto;

final public class PointCreateStringDto {

    private final String pointString;

    public class Builder {
        private String pointString;

        public Builder pointString(final String pointString) {
            this.pointString = pointString;
            return this;
        }

        public PointCreateStringDto build() {
            return new PointCreateStringDto(this);
        }
    }

    public PointCreateStringDto(Builder builder) {
        this.pointString = builder.pointString;
    }

    public String getPointString() {
        return pointString;
    }
}
