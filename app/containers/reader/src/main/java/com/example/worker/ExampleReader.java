package com.example.worker;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Example")
public class ExampleReader {

	private final String id;
	private final String name;

	private final String address;

	public ExampleReader(String id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "ExampleReader{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}

