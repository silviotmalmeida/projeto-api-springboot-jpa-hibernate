package com.silviotmalmeida.app.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.silviotmalmeida.app.entities.Order;
import com.silviotmalmeida.app.entities.User;
import com.silviotmalmeida.app.entities.enums.OrderStatus;
import com.silviotmalmeida.app.repositories.OrderRepository;
import com.silviotmalmeida.app.repositories.UserRepository;

// classe de configuração do ambiente test
// será utilizada para database seeding implementando o CommandLineRunner
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	// injetando o repository da entidade User
	@Autowired
	private UserRepository userRepository;

	// injetando o repository da entidade Order
	@Autowired
	private OrderRepository orderRepository;

	// método da interface para permitir o database seeding no início da execução
	@Override
	public void run(String... args) throws Exception {

		// criando os usuários
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		// salvando no BD
		userRepository.saveAll(Arrays.asList(u1, u2));

		// criando os pedidos
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.CANCELED);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.DELIVERED);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.PAID);

		// salvando no BD
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}
