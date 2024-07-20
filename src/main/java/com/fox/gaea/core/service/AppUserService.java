package com.fox.gaea.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fox.gaea.core.model.AdditionalInfo;
import com.fox.gaea.core.model.AppUser;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService
{
    private final AppUserRepository appUserRepository;


    static final String USER_NOT_FOUND_MSG = "User not found with id ";

    public AppUser saveAppUser(AppUser appUser)
    {
        //initialize additional info for user
        AdditionalInfo addInfo = new AdditionalInfo();
        addInfo.setAppUser(appUser);
        appUser.setAdditionalInfo(addInfo);

        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findUserByEmail(String email)
    {
        return appUserRepository.findByEmail(email);
    }
}