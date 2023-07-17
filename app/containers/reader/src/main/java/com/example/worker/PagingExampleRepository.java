package com.example.worker;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagingExampleRepository extends ListPagingAndSortingRepository<ExampleReader, String> {

}
