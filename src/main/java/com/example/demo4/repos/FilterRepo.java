package com.example.demo4.repos;

import com.example.demo4.domain.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilterRepo extends JpaRepository<Filter,Long> {

}
