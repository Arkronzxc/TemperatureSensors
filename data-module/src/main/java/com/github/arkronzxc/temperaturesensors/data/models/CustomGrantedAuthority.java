package com.github.arkronzxc.temperaturesensors.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authorities_table")
public class CustomGrantedAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public CustomGrantedAuthority(Role authority) {
        this.authority = authority;
    }
}
