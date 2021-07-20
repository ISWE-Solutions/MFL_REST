package com.moh.mfl.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Francis Chulu
 */
@Entity
@Table(name = "MFL_facility")
@EntityListeners(AuditingEntityListener.class)
public class FacilityByProvince implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long count;
    private String province;

    public FacilityByProvince() {
    }

    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * @return the type
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param type the type to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

}
