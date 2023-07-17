package com.example.worker;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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
		System.out.println(">>>> Writer");
		System.out.println("client = " + client);
		for (int i = 0; i < 10; ++i) {
			var element = new Example("hash_" + i,  "name" + i, "lastname" + i);
			crudExampleRepository.save(element);
			System.out.println("Saved!" + element);
		}

		System.out.println("Ended!");

	}
}
