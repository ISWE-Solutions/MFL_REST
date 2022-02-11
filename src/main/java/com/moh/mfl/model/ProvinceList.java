package com.moh.mfl.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Francis Chulu
 */

@Entity
@Table(name = "geography_province")
@EntityListeners(AuditingEntityListener.class)
public class ProvinceList implements Serializable {

    //@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    
    public ProvinceList(){
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

   
}
