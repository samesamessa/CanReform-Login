package org.iclass.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor


public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    private String password;
    private String username;
    private String email;
    private String role;

    @CreatedDate
    private LocalDateTime createdAt;
    private String is_active;
}
