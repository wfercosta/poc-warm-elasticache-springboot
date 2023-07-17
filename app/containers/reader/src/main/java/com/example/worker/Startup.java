package com.example.worker;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Startup {

	private final RedisTemplate client;
	private final PagingExampleRepository pagingExampleRepository;
	private final CrudExampleRepository crudExampleRepository;

	public Startup(RedisTemplate client, PagingExampleRepository pagingExampleRepository, CrudExampleRepository crudExampleRepository) {
		this.client = client;
		this.pagingExampleRepository = pagingExampleRepository;
		this.crudExampleRepository = crudExampleRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void run() {
		System.out.println(">>>> Reader");
		System.out.println("client = " + client);

		final var all = pagingExampleRepository.findAll(Sort.by("id").descending());

		System.out.println(all);

		final var paged = pagingExampleRepository.findAll(PageRequest.of(2, 1, Sort.by("id").ascending()));

		System.out.println(paged);
		System.out.println(paged.get().collect(Collectors.toList()));

		System.out.println("ids: " + crudExampleRepository.findAllById(List.of("hash_0", "hash_1", "hash_2")));

	}
}
