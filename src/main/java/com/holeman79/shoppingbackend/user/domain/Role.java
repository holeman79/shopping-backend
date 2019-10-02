package com.holeman79.shoppingbackend.user.domain;

import com.holeman79.shoppingbackend.user.domain.enums.RoleType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleType name;

    public Role() {

    }

    public Role(RoleType name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }
}
