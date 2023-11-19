package org.tptacs.application.events;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedEventHandler implements ApplicationListener<OrderCreatedEvent> {
    @Override
    @Async
    public void onApplicationEvent(OrderCreatedEvent event) {

        // Invocar a MONGO
        System.out.println("manejar evento de creación de pedido");

    }

}
