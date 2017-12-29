package com.javarnd.hrmis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
* Base entity that has a default identifier specification.
* Extends AbstractEntity to have all those properties, main
* purpose of this class was to alleviate the burden of
* defining the identifier property on the end user.
*
* Class techincally should not be abstract since there
* are not any abstract methods - it is a complete entity.
* However it is useless to have this in the database by
* itself so the constructor is kept protected.
*
*/
@MappedSuperclass
public class IdEntity<PK extends Serializable> extends
      BaseEntity<PK> {

   private static final long serialVersionUID = 1L;

   /**
    * Default identifier property, no specified
    * sql or attributes.
    */
   @Override
   @Id
   @GeneratedValue(generator = IdGen.NAME)
   @Column(name="id", nullable=false, insertable=false, updatable=false)
   public PK getId() {
      return this.id;
   }


   /**
    * Constructors are protected, class
    * must be extended to use.
    */
   protected IdEntity()  {
      this(null);
   }

   protected IdEntity(PK id)  {
      super(id);
   }
}