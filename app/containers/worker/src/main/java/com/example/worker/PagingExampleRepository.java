package com.example.worker;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingExampleRepository extends ListPagingAndSortingRepository<Example, String> {
}
