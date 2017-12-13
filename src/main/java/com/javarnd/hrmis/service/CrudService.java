package com.javarnd.hrmis.service;

import java.util.List;

public interface CrudService<TEntity, TModel> {
    TModel findOne(Long id);
 
    List<TModel> findAll();
 
    TModel update(TModel model);
 
    void delete(Long id);
 
    TModel create(TModel model);
}