package com.javarnd.hrmis.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.javarnd.hrmis.model.BaseEntity;
import com.javarnd.hrmis.repository.BaseRepository;
import com.javarnd.hrmis.service.CrudService;

public class CrudServiceImpl<TEntity extends BaseEntity, TModel> implements CrudService<TEntity, TModel> {
    @Autowired
    protected BaseRepository<TEntity> repository;
 
    @Autowired
    protected ModelMapper modelMapper;
 
    protected Class<TEntity> entityClass;
    protected Class<TModel> modelClass;
 
    @SuppressWarnings("unchecked")
    public CrudServiceImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<TEntity>) genericSuperclass.getActualTypeArguments()[0];
        this.modelClass = (Class<TModel>) genericSuperclass.getActualTypeArguments()[1];
    }
 
    public TModel findOne(Long id) {
        TEntity entity = repository.findOne(id);
        return modelMapper.map(entity, modelClass);
    }
 
    public List<TModel> findAll() {
        List<TModel> result = new ArrayList<>();
        List<TEntity> entities = repository.findAll();
        for (TEntity entity : entities) {
            result.add(modelMapper.map(entity, modelClass));
        }
        return result;
    }
 
    public TModel update(TModel model) {
        TEntity entity = modelMapper.map(model, entityClass);
        beforeUpdate(entity);
        return modelMapper.map(repository.saveAndFlush(entity), modelClass);
    }
 
    public void delete(Long id) {
        /*TEntity entity = repository.findOne(id);
        repository.delete(entity);*/
    	repository.delete(id);
    }
 
    public TModel create(TModel model) {
        TEntity entity = modelMapper.map(model, entityClass);
        beforeAdd(entity);
        return modelMapper.map(repository.saveAndFlush(entity), modelClass);
    }
 
    protected void beforeUpdate(TEntity entity) {
        entity.setDateUpdated(new Date());
    }
 
    protected void beforeAdd(TEntity entity) {
        entity.setDateCreated(new Date());
    }
    
}