package com.allinformatica.products.repositories;

import com.allinformatica.products.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UsersModel, UUID> {
    UserDetails findByLogin(String login);
}
