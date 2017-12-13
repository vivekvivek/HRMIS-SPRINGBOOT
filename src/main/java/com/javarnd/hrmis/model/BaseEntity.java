package com.javarnd.hrmis.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseEntity implements Serializable {
    
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    private Long dateCreated;
    private Long dateUpdated;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Long dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
}