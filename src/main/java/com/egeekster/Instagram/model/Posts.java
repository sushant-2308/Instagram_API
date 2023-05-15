package com.egeekster.Instagram.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Entity
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
}
