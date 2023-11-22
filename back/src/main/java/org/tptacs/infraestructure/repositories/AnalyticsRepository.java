package org.tptacs.infraestructure.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.tptacs.infraestructure.repositories.interfaces.IAnalyticsRepository;
import org.tptacs.infraestructure.repositories.interfaces.IOrderRepository;
import org.tptacs.infraestructure.repositories.interfaces.IUserRepository;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;

@Repository
public class AnalyticsRepository implements IAnalyticsRepository {

    Jedis jedis;

    private final IOrderRepository orderRepository;

    private final IUserRepository userRepository;



    @Autowired
    public AnalyticsRepository(@Value("${redis.host}") String redisHost,
                               @Value("${redis.port}") int redisPort,
                               @Value("${redis.user}") String redisUser,
                               @Value("${redis.pass}") String redisPassword,
                               IOrderRepository orderRepository,
                               IUserRepository userRepository) {
        JedisClientConfig config = DefaultJedisClientConfig.builder()
                .user(redisUser)
                .password(redisPassword)
                .build();
        jedis = new Jedis(redisHost, redisPort, config);

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        initializeCounters();
    }

    @Override
    public void addUser() {
        jedis.incr("users");
    }

    @Override
    public void addOrder() {
        jedis.incr("orders");
    }

    @Override
    public long countUsers() {
        String users = jedis.get("users");
        return Long.parseLong(users);
    }

    @Override
    public long countOrders() {
        String orders = jedis.get("orders");
        return Long.parseLong(orders);
    }

    private void initializeCounters() {
        var a = userRepository.toString();

        var usersCount = userRepository.countUserUnique().toString();
        var ordersCount = orderRepository.count().toString();

        jedis.set("users", usersCount);
        jedis.set("orders", ordersCount);
    }
}