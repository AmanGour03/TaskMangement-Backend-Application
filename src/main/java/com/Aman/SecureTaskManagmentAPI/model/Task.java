package com.Aman.SecureTaskManagmentAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @NotBlank
    private String title;
    @NonNull
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    @NonNull
    @NotBlank
    private Status status;
    @NonNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Future(message = "Deadline should be of future")
    private LocalDateTime deadline;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Past(message = "Date cannot be of the past")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;
}
