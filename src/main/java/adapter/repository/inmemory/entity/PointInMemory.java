package adapter.repository.inmemory.entity;

import adapter.repository.inmemory.EntityInMemory;

public class PointInMemory extends EntityInMemory {
    private final Integer row;
    private final Integer column;
    private final String pointString;

    public static class Builder extends EntityInMemory.Builder<Builder> {
        private String id;
        private Integer row;
        private Integer column;
        private String pointString;

        public Builder id(final String id) {
            this.id = id;
            return self();
        }

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

}
