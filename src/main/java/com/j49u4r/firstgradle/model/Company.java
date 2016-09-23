package com.j49u4r.firstgradle.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by j49u4r on 9/22/16.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 5204067864656903495L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "rfc")
    private String rfc;

    public Company() {
    }

    public Company(String name, String rfc) {
        this.id = id;
        this.name = name;
        this.rfc = rfc;
    }

    public Company(Integer id, String name, String rfc) {
        this.id = id;
        this.name = name;
        this.rfc = rfc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Clase builder de una instancia de CompanyGroovy.
     */

    public static class CompanyBuilder {

        private String name;
        private String rfc;

        public CompanyBuilder() {
        }

        public CompanyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CompanyBuilder rfc(String rfc) {
            this.rfc = rfc;
            return this;
        }

        public Company build() {
            return new Company(name, rfc);
        }
    }
}
