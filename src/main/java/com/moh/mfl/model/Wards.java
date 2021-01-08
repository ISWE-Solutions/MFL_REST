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
@Table(name = "geography_ward")
@EntityListeners(AuditingEntityListener.class)
public class Wards implements Serializable {

    //@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotBlank
    @Column(name = "population")
    private String population;
    @Column(name = "pop_density")
    private String populationDensity;
    @Column(name = "area_sq_km")
    private String areaSquareKilometer;
    @Column(name = "geom")
    private String geometricalCoordinates;
    @Column(name = "constituency")
    private String constituency;
    @Column(name = "district")
    private String district;
    @Column(name = "province")
    private String province;

    public Wards() {
        
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
     * @return the population
     */
    public String getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * @return the populationDensity
     */
    public String getPopulationDensity() {
        return populationDensity;
    }

    /**
     * @param populationDensity the populationDensity to set
     */
    public void setPopulationDensity(String populationDensity) {
        this.populationDensity = populationDensity;
    }

    /**
     * @return the areaSquareKilometer
     */
    public String getAreaSquareKilometer() {
        return areaSquareKilometer;
    }

    /**
     * @param areaSquareKilometer the areaSquareKilometer to set
     */
    public void setAreaSquareKilometer(String areaSquareKilometer) {
        this.areaSquareKilometer = areaSquareKilometer;
    }

    /**
     * @return the geometricalCoordinates
     */
    public String getGeometricalCoordinates() {
        return geometricalCoordinates;
    }

    /**
     * @param geometricalCoordinates the geometricalCoordinates to set
     */
    public void setGeometricalCoordinates(String geometricalCoordinates) {
        this.geometricalCoordinates = geometricalCoordinates;
    }

    /**
     * @return the constituency
     */
    public String getConstituency() {
        return constituency;
    }

    /**
     * @param constituency the constituency to set
     */
    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

}
