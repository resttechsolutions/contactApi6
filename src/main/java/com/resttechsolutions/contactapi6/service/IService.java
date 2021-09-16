package com.resttechsolutions.contactapi6.service;

import java.util.List;

public interface IService <T, P>{

    T create(T t) throws Exception;
    T findById(P p) throws Exception;
    List<T> findAll() throws Exception;
    T update(T t) throws Exception;
    void delete(P p) throws Exception;

}
