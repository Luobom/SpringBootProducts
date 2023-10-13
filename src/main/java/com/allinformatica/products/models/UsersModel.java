package com.allinformatica.products.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersModel extends RepresentationModel<UsersModel>  implements Serializable, UserDetails {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUsers;
    @Column(nullable = false, unique = true, length = 100)
    private String login;
    @Column(nullable = false, unique = false, length = 100)
    private String passwd;
    @Column(nullable = false, unique = false, length = 100)
    private UserRolesModel role;


    public UsersModel(){

    }
    public UsersModel(UUID idUsers, String login, String passwd, UserRolesModel role){
        this.idUsers = idUsers;
        this.login = login;
        this.passwd = passwd;
        this.role = role;
    }

    public UsersModel(String login, String passwd, UserRolesModel role){
        this.login = login;
        this.passwd = passwd;
        this.role = role;
    }


    // getter and settes
    public UUID getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(UUID idUsers) {
        this.idUsers = idUsers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public UserRolesModel getRole() {
        return role;
    }

    public void setRole(UserRolesModel role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "idUsers=" + idUsers +
                ", login='" + login + '\'' +
                ", passwd='" + passwd + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


    // UserDetails Methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRolesModel.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
        return login;
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
