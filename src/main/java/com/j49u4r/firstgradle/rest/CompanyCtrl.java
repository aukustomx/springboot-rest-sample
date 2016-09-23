package com.j49u4r.firstgradle.rest;

import com.j49u4r.firstgradle.service.CompanyService;
import com.j49u4r.firstgradle.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Responsable de las operaciones realizadas sobre las empresas registradas en
 * la aplicación.
 * Created by j49u4r on 9/22/16.
 */
@RestController
@RequestMapping(value = "/api/company")
public class CompanyCtrl {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public Object save(@RequestBody CompanyRequest req) {
        return companyService.save(req);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable int id) {
        return companyService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = PUT, produces = APPLICATION_JSON_VALUE)
    public Object update(@PathVariable int id, @RequestBody CompanyRequest req) {
        return companyService.update(id, req);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Object byId(@PathVariable int id) {
        return companyService.byId(id);
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public Object all() {
        return companyService.all();
    }
    private String genericResponse() {
        return Json.createObjectBuilder()
                .add("responseCode", HttpStatus.OK.value())
                .add("message", HttpStatus.OK.getReasonPhrase())
                .build()
                .toString();
    }
}
