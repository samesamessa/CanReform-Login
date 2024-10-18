package org.iclass.board.dto;

import org.iclass.board.entity.LoginEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class UserDTO {

    private long user_id;
    private String password;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;
    private String is_active;

    public static UserDTO toDTO(LoginEntity entity) {
        return (
                UserDTO.builder()
                        .createdAt(entity.getCreatedAt())
                        .email(entity.getEmail())
                        .role(entity.getRole())
                        .is_active(entity.getIs_active())
                        .user_id(entity.getUser_id())
                        .password(entity.getPassword())
                        .username(entity.getUsername())
                        .build()
        );
    }

    public LoginEntity toEntity(){
        return (
                LoginEntity.builder()
                        .createdAt(this.createdAt)
                        .email(this.email)
                        .role(this.role)
                        .is_active(this.is_active)
                        .user_id(this.user_id)
                        .password(this.password)
                        .username(this.username)
                        .build()
        );
    }

}
