package org.example.clinica_estetica.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "funcionario")
@SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario", initialValue = 1, allocationSize = 1)
public class Funcionario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario")
    private Long id;

    private String login;

    private String senha;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "funcionarios_acesso", uniqueConstraints = @UniqueConstraint(name = "funcionario_unico_acesso",
            columnNames = {"funcionario_id", "acesso_id"}),
            joinColumns = @JoinColumn(name = "funcionario_id",
                    referencedColumnName = "id",
                    table = "funcionario",
                    unique = false,
                    foreignKey = @ForeignKey(name = "funcionario_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "acesso_id",
                    referencedColumnName = "id",
                    table = "acesso",
                    unique = false,
                    foreignKey = @ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
    private List<Acesso> acessos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.acessos;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
