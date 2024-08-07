package com.fox.gaea.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fox.gaea.core.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long>
{
    @Query("SELECT u FROM AppUser u WHERE u.appUserEmail = ?1")
    Optional<AppUser> findByEmail(String email);

    @Query("SELECT u FROM AppUser u WHERE u.additionalInfo.pictureId IS NOT NULL ORDER BY u.id DESC")
    Page<AppUser> findTopByPictureIdIsNotNullOrderByIdDesc(Pageable pageable);
}