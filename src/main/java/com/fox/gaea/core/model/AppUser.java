package com.fox.gaea.core.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser
{
    @Id
    @GeneratedValue
    private Long id;

    private String appUserName;
    private String appUserEmail;
    private String nameIdentifier;
    private boolean receiveNews;

    //relationschip
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AppUser> friends = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AdditionalInfo additionalInfo;
}