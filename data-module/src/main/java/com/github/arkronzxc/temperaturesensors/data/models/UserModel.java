package com.github.arkronzxc.temperaturesensors.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_table")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Email
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JoinColumn(name = "role_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CustomGrantedAuthority authority;

    public UserModel(UserRegistrationModel model) {
        this.username = model.getUsername();
        this.email = model.getEmail();
        this.password = model.getPassword();
        this.authority = new CustomGrantedAuthority(Role.USER);
    }
}
