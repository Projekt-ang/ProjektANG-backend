package pl.ang.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class MassRegisterWrapper {
    List<User> users;
    String role;
}
