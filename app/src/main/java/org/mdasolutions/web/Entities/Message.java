package org.mdasolutions.web.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "recipient", nullable = false)
    private String recipient;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
