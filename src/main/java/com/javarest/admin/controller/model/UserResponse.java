package com.javarest.admin.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(@JsonProperty("id") Long id,@JsonProperty("firstName") String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("email") String email) {}
