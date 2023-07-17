package com.example.worker;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Example")
public class Example {

	private final String id;
	private final String name;

	private final String lastname;

	public Example(String id, String name, String lastname) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		return "Example{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				'}';
	}
}

