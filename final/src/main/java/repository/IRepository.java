package repository;

import domain.Entity;

public interface IRepository<ID,E extends Entity<ID>> {
    ID create(E entity);
    Iterable<E> getAll();

}
