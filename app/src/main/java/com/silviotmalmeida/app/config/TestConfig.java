package com.silviotmalmeida.app.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.silviotmalmeida.app.entities.Category;
import com.silviotmalmeida.app.entities.Order;
import com.silviotmalmeida.app.entities.OrderItem;
import com.silviotmalmeida.app.entities.Payment;
import com.silviotmalmeida.app.entities.Product;
import com.silviotmalmeida.app.entities.User;
import com.silviotmalmeida.app.entities.enums.OrderStatus;
import com.silviotmalmeida.app.repositories.CategoryRepository;
import com.silviotmalmeida.app.repositories.OrderItemRepository;
import com.silviotmalmeida.app.repositories.OrderRepository;
import com.silviotmalmeida.app.repositories.ProductRepository;
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

	// injetando o repository da entidade Category
	@Autowired
	private CategoryRepository categoryRepository;

	// injetando o repository da entidade Product
	@Autowired
	private ProductRepository productRepository;

	// injetando o repository da entidade OrderItem
	@Autowired
	private OrderItemRepository orderItemRepository;

	// método da interface para permitir o database seeding no início da execução
	@Override
	public void run(String... args) throws Exception {

		// criando os usuários
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		// salvando no BD
		userRepository.saveAll(Arrays.asList(u1, u2));

		// criando os pedidos
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);

		// salvando no BD
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		// criando as categorias
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		// salvando no BD
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		// criando os produtos
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		// salvando no BD
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		// inserindo os relacionamentos entre categorias e produtos
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		// salvando no BD
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		// inserindo os dados dos itens de pedido
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

		// salvando no BD
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

		// criando o pagamento do pedido o1
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);

		// salvando no BD
		orderRepository.saveAll(Arrays.asList(o1));
	}
}
