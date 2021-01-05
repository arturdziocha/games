package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.common.domain.Entity;

final class Point extends Entity {

    private final Integer row;
    private final Integer column;
    private final String pointString;

    class Builder extends Entity.Builder<Builder> {
        private Integer row;
        private Integer column;
        private String pointString;

        Builder row(final Integer row) {
            this.row = row;
            return self();
        }

        Builder column(final Integer column) {
            this.column = column;
            return self();
        }

        Builder pointString(final String pointString) {
            this.pointString = pointString;
            return self();
        }

        @Override
        protected Point build() {
            return new Point(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

    }

    public Point(Builder builder) {
        super(builder);
        this.row = builder.row;
        this.column = builder.column;
        this.pointString = builder.pointString;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public String getPointString() {
        return pointString;
    }

}
