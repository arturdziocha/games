package com.ara.game.usecases.battleship.point.dto;

final public class PointCreateRowColDto {

    private final Integer row;
    private final Integer column;

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

    public static Builder builder() {
        return new Builder();
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((column == null) ? 0 : column.hashCode());
        result = prime * result + ((row == null) ? 0 : row.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PointCreateRowColDto other = (PointCreateRowColDto) obj;
        if (column == null) {
            if (other.column != null)
                return false;
        } else if (!column.equals(other.column))
            return false;
        if (row == null) {
            return other.row == null;
        } else return row.equals(other.row);
    }

}
