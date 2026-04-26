package com.Aman.SecureTaskManagmentAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @NotBlank(message = "Cannot be a blank username")
    private String username;
    @NonNull
    @NotBlank
    @Email(message = "The email is not in the right format.")
    private String email;
    @NonNull
    @NotBlank
    @Size(min = 6,max = 20,message = "The password should be in between 6 to 20 characters.")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
