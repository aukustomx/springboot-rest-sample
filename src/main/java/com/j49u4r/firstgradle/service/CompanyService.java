package com.j49u4r.firstgradle.service;

import com.j49u4r.firstgradle.rest.CompanyRequest;

/**
 * Define las operaciones que se hacen sobre las empresas de la aplicación.
 * Created by j49u4r on 9/22/16.
 */
public interface CompanyService {

    Object save(CompanyRequest req);

    Object update(int id, CompanyRequest req);

    Object delete(int id);

    Object all();

    Object byId(int id);
}
