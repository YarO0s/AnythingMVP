package com.denisov.anything.authservice;

public interface DataMapperInterface<Entity, Domain> {
    public Entity domainToEntity(Domain domain);

    public Domain entityToDomain(Entity entity);
}
