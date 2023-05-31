package com.redditclone.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerficationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Instant expriyDate;
}
