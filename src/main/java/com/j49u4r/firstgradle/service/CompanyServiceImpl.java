package com.j49u4r.firstgradle.service;

import com.j49u4r.firstgradle.model.Company;
import com.j49u4r.firstgradle.repository.CompanyRepository;
import com.j49u4r.firstgradle.rest.CompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import java.util.Collections;

/**
 * Created by j49u4r on 9/22/16.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Object save(CompanyRequest req) {

       Company company = new Company.CompanyBuilder()
                .name(req.getName())
                .rfc(req.getRfc())
                .build();

        company = companyRepository.save(company);

        return genericResponse()
                .add("company", companyToJsonObjectBuilder(company))
                .build()
                .toString();
    }

    @Override
    public Object update(int id, CompanyRequest req) {
        return null;
    }

    @Override
    public Object delete(int id) {
        return null;
    }

    @Override
    public Object all() {

        JsonBuilderFactory builderFactory = Json.createBuilderFactory(Collections.emptyMap());

        JsonArrayBuilder builder = companyRepository.findAll()
                .stream()
                .map(this::companyToJsonObjectBuilder)
                .collect(builderFactory::createArrayBuilder,
                        (a, s) -> a.add(s),
                        (b1, b2) -> b1.add(b2));

        return genericResponse()
                .add("companies", builder)
                .build()
                .toString();
    }

    @Override
    public Object byId(int id) {
        return null;
    }

    private JsonObjectBuilder genericResponse() {
        return Json.createObjectBuilder()
                .add("responseCode", HttpStatus.OK.value())
                .add("message", HttpStatus.OK.getReasonPhrase());
    }

    private JsonObjectBuilder companyToJsonObjectBuilder(final Company company) {
        return Json.createObjectBuilder()
                .add("id", company.getId())
                .add("name", company.getName())
                .add("rfc", company.getRfc());
    }
}
