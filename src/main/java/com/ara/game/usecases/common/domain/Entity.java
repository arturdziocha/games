package com.ara.game.usecases.common.domain;

public abstract class Entity {
    final String id;

    public abstract static class Builder<T extends Builder<T>> {
        protected String id;

        public T id(String id) {
            this.id = id;
            return self();
        }

        protected abstract Entity build();

        protected abstract T self();
    }

    public Entity(Builder<?> builder) {
        id = builder.id;
    }

    public String getId() {
        return id;
    }
}