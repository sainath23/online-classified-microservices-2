package com.doitgeek.onlineclassified.occategoryservice.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID extends Serializable> {
    Optional<T> findById(ID id);
    List<T> findAll();
    T save(T t);
    void deleteById(ID id);
}
