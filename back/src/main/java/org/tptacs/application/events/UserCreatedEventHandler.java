package org.tptacs.application.events;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedEventHandler implements ApplicationListener<UserCreatedEvent> {
    @Override
    @Async
    public void onApplicationEvent(UserCreatedEvent event) {

        // INVOCAR A MONGO
        System.out.println("manejar evento de creación de usuario");
    }
}
