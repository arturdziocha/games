package com.ara.game.usecases.common;

import org.apache.commons.lang3.StringUtils;

public final class CreateDto {
    private final String id;

    public static class Builder {
        private String id;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public CreateDto build() {
            if (StringUtils.isEmpty(id)) {
                throw new IllegalArgumentException("Id cannot be empty");
            }
            return new CreateDto(this);
        }
    }

    public CreateDto(Builder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return id;
    }
}
