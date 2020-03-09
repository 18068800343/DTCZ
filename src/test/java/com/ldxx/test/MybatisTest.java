package com.ldxx.test;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MybatisTest {

	@Test
	public void mybatisMethod(){
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date = localDateTime.format(dateTimeFormatter);
		System.out.println(date);
	}

	@Autowired
	private JedisPool jedisPool;

	@Test
	public void jedisTest(){
		Jedis jedis = jedisPool.getResource();
		jedis.set("color","red");
		String color = jedis.get("color");
		System.out.println(color);
	}

	public static void main(String[] args) {
	}
}
