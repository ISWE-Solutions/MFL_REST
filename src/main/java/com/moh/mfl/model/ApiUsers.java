/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.mfl.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chulu
 */
@Entity
@Table(name = "api_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApiUsers.findAll", query = "SELECT a FROM ApiUsers a"),
    @NamedQuery(name = "ApiUsers.findById", query = "SELECT a FROM ApiUsers a WHERE a.id = :id"),
    @NamedQuery(name = "ApiUsers.findByUsername", query = "SELECT a FROM ApiUsers a WHERE a.username = :username"),
    @NamedQuery(name = "ApiUsers.findByPassword", query = "SELECT a FROM ApiUsers a WHERE a.password = :password"),
    @NamedQuery(name = "ApiUsers.findByAuthKey", query = "SELECT a FROM ApiUsers a WHERE a.authKey = :authKey"),
    @NamedQuery(name = "ApiUsers.findByStatus", query = "SELECT a FROM ApiUsers a WHERE a.status = :status"),
    @NamedQuery(name = "ApiUsers.findByCreatedBy", query = "SELECT a FROM ApiUsers a WHERE a.createdBy = :createdBy"),
    @NamedQuery(name = "ApiUsers.findByCreatedAt", query = "SELECT a FROM ApiUsers a WHERE a.createdAt = :createdAt"),
    @NamedQuery(name = "ApiUsers.findByUpdatedBy", query = "SELECT a FROM ApiUsers a WHERE a.updatedBy = :updatedBy"),
    @NamedQuery(name = "ApiUsers.findByUpdatedAt", query = "SELECT a FROM ApiUsers a WHERE a.updatedAt = :updatedAt")})

public class ApiUsers implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "auth_key")
    private String authKey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "email")
    private String email;

    public ApiUsers() {
    }

    public ApiUsers(Long id) {
        this.id = id;
    }

    public ApiUsers(Long id, String username, String password, String authKey, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authKey = authKey;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApiUsers)) {
            return false;
        }
        ApiUsers other = (ApiUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.moh.mfl.model.ApiUsers[ id=" + id + " ]";
    }
    
}
