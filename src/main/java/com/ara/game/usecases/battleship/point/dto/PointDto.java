package com.ara.game.usecases.battleship.point.dto;

public final class PointDto implements Comparable<PointDto> {
    private final String id;
    private final String pointString;
    private final Integer row;
    private final Integer column;

    private PointDto(Builder builder) {
        this.id = builder.id;
        this.pointString = builder.pointString;
        this.row = builder.row;
        this.column = builder.column;
    }

    public String getId() {
        return id;
    }

    public String getPointString() {
        return pointString;
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

    @Override
    public String toString() {
        return "PointDto [id=" + id + ", pointString=" + pointString + ", row=" + row + ", column=" + column + "]";
    }

    @Override
    public int compareTo(PointDto o) {
        int result = row.compareTo(o.getRow());
        if (result == 0) {
            result = column.compareTo(o.getColumn());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointDto pointDto = (PointDto) o;

        if (!id.equals(pointDto.id)) return false;
        if (!pointString.equals(pointDto.pointString)) return false;
        if (!row.equals(pointDto.row)) return false;
        return column.equals(pointDto.column);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + pointString.hashCode();
        result = 31 * result + row.hashCode();
        result = 31 * result + column.hashCode();
        return result;
    }

    public static class Builder {
        private String id;
        private String pointString;
        private Integer row;
        private Integer column;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder pointString(final String pointString) {
            this.pointString = pointString;
            return this;
        }

        public Builder row(final Integer row) {
            this.row = row;
            return this;
        }

        public Builder column(final Integer column) {
            this.column = column;
            return this;
        }

        public PointDto build() {
            return new PointDto(this);
        }
    }
}
