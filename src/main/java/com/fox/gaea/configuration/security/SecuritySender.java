package com.fox.gaea.configuration.security;
import org.springframework.context.ApplicationEvent;

import com.fox.gaea.configuration.security.user.User;

public class SecuritySender extends ApplicationEvent
{
    private final User user;

    public SecuritySender(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}