package com.ara.game.usecases.battleship.point.dto;

final public class PointCreateRowColDto {

    private final Integer row;
    private final Integer column;

    public static class Builder {
        private Integer row;
        private Integer column;

        public Builder row(final Integer row) {
            this.row = row;
            return this;
        }

        public Builder column(final Integer column) {
            this.column = column;
            return this;
        }

        public PointCreateRowColDto build() {
            return new PointCreateRowColDto(this);
        }
    }

    private PointCreateRowColDto(Builder builder) {
        this.row = builder.row;
        this.column = builder.column;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }
}
