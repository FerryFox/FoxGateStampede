package com.fox.gaea.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String status;
    private String connection;
    private String pictureId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id",  nullable = true)
    private AppUser appUser;
}