package com.practice_spring_boot_3.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();
    Optional<T> findById(int id);
    void save(T t);
    void delete(int id);
}
