package com.javarnd.hrmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.javarnd.hrmis.model.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<TEntity extends BaseEntity> extends JpaRepository<TEntity, Long> {
}