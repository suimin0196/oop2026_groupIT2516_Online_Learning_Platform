package com.company.services;

import com.company.repositories.BaseRepository;
import java.util.List;

// Generic base service implementation that delegates common CRUD to repository
public abstract class AbstractBaseService<T, ID> implements BaseService<T, ID> {
    protected final BaseRepository<T, ID> repo;

    protected AbstractBaseService(BaseRepository<T, ID> repo) {
        this.repo = repo;
    }

    @Override
    public void create(T entity) {
        repo.create(entity);
    }

    @Override
    public T getById(ID id) {
        return repo.findById(id);
    }

    @Override
    public void update(T entity) {
        repo.update(entity);
    }

    @Override
    public List<T> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(ID id) {
        repo.delete(id);
    }

    @Override
    public boolean exists(ID id) {
        return repo.exists(id);
    }
}
