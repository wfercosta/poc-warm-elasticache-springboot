package com.example.worker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudExampleRepository extends CrudRepository<Example, String> {
}
