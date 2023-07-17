package com.example.worker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudExampleRepository extends CrudRepository<ExampleReader, String> {


}
