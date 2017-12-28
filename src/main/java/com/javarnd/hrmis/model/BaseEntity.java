package com.javarnd.hrmis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.LoggerFactory;

import com.javarnd.hrmis.controller.EmployeeController;

/**
* Abstract base class for all database entities.  Keeps track
* of creation date, last modification date, allows 'deleted' objects
* to remain in the database w/o destroying existing relationships.
* Primary key property is flexible in terms of type, generation strategy
* and custom attributes.
* 
* The main purposes of this class are to keep common properties in one
* place, give the middle layer a better bound to go by than <? extends Object>,
* have proper implementations of equals and hashcode for collections, and
* most importantly not impose any constraints on the identity property for the
* end user.
*
* Subclasses must define getId() & place all annotations on getter
* methods.
*/
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> implements
      Serializable {

   // log4j is available to all subclasses
   //protected transient final Log LOG = LogFactory.getLog(getClass());
	protected transient final org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeController.class);

   protected PK id;         // the objects primary key
   @Transient               // marked as transient so hibernate won't
   public abstract PK getId();   // think this is the ID (crashes w/o it)


   protected Date created;      // when the entity was originally persisted


   protected Date modified;   // last time it was modifiied


   protected Boolean deleted;   // whether or not the object is deleted,
                        // useful for retaining exsisting relationships
                        // and not showing deleted stuff to the user


   /**
    * We employ some intelligence in here, if
    * deleted was a boolean and the column is null
    * it fails.  So we use a Boolean which permits nulls,
    * and just treat null = false.
    */
   public void setDeleted(Boolean deleted) {
      this.deleted = (deleted == null ? false : deleted);
   }


   /**
    * Default constructor, does nothing
    */
   public BaseEntity() {
      this(null);
   }


   /**
    * Construct a new entity with a given PK
    * @param id the primary key
    */
   public BaseEntity(PK id) {
      super();
      this.id = id;
   }


   /**
    * Stripes likes the id for toString to populate
    * select boxes.  This can be overridden if not needed.
    */
   @Override
   public String toString() {
      return getId().toString();
   }


   /**
    * Use commons-lang for a reflexive equals
    */
   @Override
   public boolean equals(final Object that) {
      return EqualsBuilder.reflectionEquals(this, that);
   }


   /**
    * Use commons-lang for reflexive hashCode
    */
   @Override
   public int hashCode() {
      return HashCodeBuilder.reflectionHashCode(this);
   }



   // useless accessor methods


   public Date getCreated() {
      return this.created;
   }



   public void setCreated(Date created) {
      this.created = created;
   }



   public Boolean getDeleted() {
      return this.deleted;
   }



   public void setId(PK id) {
      this.id = id;
   }



   public Date getModified() {
      return this.modified;
   }



   public void setModified(Date modified) {
      this.modified = modified;
   }
}