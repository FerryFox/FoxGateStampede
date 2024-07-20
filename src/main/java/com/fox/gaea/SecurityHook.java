package com.fox.gaea;

import com.fox.gaea.configuration.security.SecuritySender;
import com.fox.gaea.configuration.security.user.User;
import com.fox.gaea.core.model.AppUser;
import com.fox.gaea.core.service.AppUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityHook
{
    @Autowired
    private final AppUserService appUserService;

    @EventListener
    public void handleSecuritySender(SecuritySender event)
    {
        User user = event.getUser();
        AppUser appUser = new AppUser();

        appUser.setAppUserName(user.getFirstname());
        appUser.setAppUserEmail(user.getEmail());
        appUser.setReceiveNews(user.isReceiveNews());
        appUser.setNameIdentifier(generateAppUserIdentifier());

        appUserService.saveAppUser(appUser);
    }

    private String generateAppUserIdentifier()
    {
        UUID id = UUID.randomUUID();
        return  id.toString();
    }
}