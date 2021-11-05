package com.moh.mfl.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Francis Chulu
 */
@Entity
@Table(name = "`facility_types`")
@EntityListeners(AuditingEntityListener.class)
public class FacilityTypes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    private Long shared_id;

    public FacilityTypes() {
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

    /**
     * @return the shared_id
     */
    public Long getShared_id() {
        return shared_id;
    }

    /**
     * @param shared_id the shared_id to set
     */
    public void setShared_id(Long shared_id) {
        this.shared_id = shared_id;
    }
}
