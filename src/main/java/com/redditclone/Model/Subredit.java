package com.redditclone.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Auditable;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subredit  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;

    private String name;

    private String description;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;



}
