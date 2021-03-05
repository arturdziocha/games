package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;

public class PointInMemory extends EntityInMemory implements Comparable<PointInMemory> {
    private final Integer row;
    private final Integer column;
    private final String pointString;


    private PointInMemory(Builder builder) {
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

    @Override
    public int compareTo(PointInMemory o) {
        int result = row - o.getRow();
        if (result == 0) {
            result = column - o.getColumn();
        }
        return result;
    }

    public static class Builder extends EntityInMemory.Builder<Builder> {
        private Integer row;
        private Integer column;
        private String pointString;

        public Builder row(final Integer row) {
            this.row = row;
            return self();
        }

        public Builder column(final Integer column) {
            this.column = column;
            return self();
        }

        public Builder pointString(final String pointString) {
            this.pointString = pointString;
            return self();
        }


        @Override
        protected PointInMemory build() {
            return new PointInMemory(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
