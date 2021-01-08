package adapter.repository.inmemory;

public class EntityInMemory {
    final String id;

    public abstract static class Builder<T extends EntityInMemory.Builder<T>> {
        protected String id;

        public T id(String id) {
            this.id = id;
            return self();
        }

        protected abstract EntityInMemory build();

        protected abstract T self();
    }

    public EntityInMemory(EntityInMemory.Builder<?> builder) {
        id = builder.id;
    }

    public String getId() {
        return id;
    }
}
