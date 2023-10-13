package com.allinformatica.products.dtos;

import com.allinformatica.products.models.UserRolesModel;

public record RegisterDTO(String login, String passwd, UserRolesModel role) {

}
