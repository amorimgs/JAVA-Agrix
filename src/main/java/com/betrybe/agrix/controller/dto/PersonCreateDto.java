package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.security.Role;

/**
 * The type Person create dto.
 */
public record PersonCreateDto(String username, String password, Role role) {

}
