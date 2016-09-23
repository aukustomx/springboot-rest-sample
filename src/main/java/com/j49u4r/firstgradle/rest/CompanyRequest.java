package com.j49u4r.firstgradle.rest;

import java.io.Serializable;

/**
 * Created by j49u4r on 9/22/16.
 */
public class CompanyRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String rfc;

    public CompanyRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
