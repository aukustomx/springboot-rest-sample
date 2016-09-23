package com.j49u4r.firstgradle.repository;

import com.j49u4r.firstgradle.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by j49u4r on 9/22/16.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
