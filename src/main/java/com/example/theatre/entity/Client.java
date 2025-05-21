package com.example.theatre.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",  nullable = false, unique = true)
    private String name;


    @Transient
    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 3, max = 15, message = "Фамилия должна быть от 3 до 15 символов")
    @Pattern(
            regexp = "^[А-ЯЁ][а-яё]*$",  // Убрал дефис: теперь только буквы
            message = "Фамилия должна начинаться с заглавной буквы и содержать только буквы"
    )
    private String lastName;

    @Transient
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 15, message = "Имя должно быть от 2 до 15 символов")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]*$",
            message = "Имя должно начинаться с заглавной буквы и содержать только буквы")
    private String firstName;

    @Transient
    @Size(min = 5, max = 15, message = "Отчество должно быть от 5 до 15 символов")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]*$",
            message = "Отчество должно начинаться с заглавной буквы и содержать только буквы")
    private String middleName;


    @PrePersist
    @PreUpdate
    private void combineName() {
        this.name = lastName + " " + firstName +
                (middleName != null && !middleName.isEmpty() ? " " + middleName : "");
    }

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    @Pattern(regexp = "^(\\+7|8)[0-9]{10}$",
            message = "Номер должен начинаться с +7 или 8 и содержать 11 цифр")
    private String phone;

    @Column(name = "password", nullable = false, unique = true)
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*]).*$",
            message = "Пароль должен содержать минимум 1 заглавную букву и 1 спецсимвол")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public @NotBlank(message = "Фамилия обязательна") @Size(min = 3, max = 15, message = "Фамилия должна быть от 3 до 15 символов") @Pattern(
            regexp = "^[А-ЯЁ][а-яё]*$",  // Убрал дефис: теперь только буквы
            message = "Фамилия должна начинаться с заглавной буквы и содержать только буквы"
    ) String getLastName() {
        return lastName;
    }

    public @NotBlank(message = "Имя обязательно") @Size(min = 2, max = 15, message = "Имя должно быть от 2 до 15 символов") @Pattern(regexp = "^[А-ЯЁ][а-яё]*$",
            message = "Имя должно начинаться с заглавной буквы и содержать только буквы") String getFirstName() {
        return firstName;
    }

    public @Size(min = 5, max = 15, message = "Отчество должно быть от 5 до 15 символов") @Pattern(regexp = "^[А-ЯЁ][а-яё]*$",
            message = "Отчество должно начинаться с заглавной буквы и содержать только буквы") String getMiddleName() {
        return middleName;
    }

    public void setLastName(@NotBlank(message = "Фамилия обязательна") @Size(min = 3, max = 15, message = "Фамилия должна быть от 3 до 15 символов") @Pattern(
            regexp = "^[А-ЯЁ][а-яё]*$",  // Убрал дефис: теперь только буквы
            message = "Фамилия должна начинаться с заглавной буквы и содержать только буквы"
    ) String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(@NotBlank(message = "Имя обязательно") @Size(min = 2, max = 15, message = "Имя должно быть от 2 до 15 символов") @Pattern(regexp = "^[А-ЯЁ][а-яё]*$",
            message = "Имя должно начинаться с заглавной буквы и содержать только буквы") String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(@Size(min = 5, max = 15, message = "Отчество должно быть от 5 до 15 символов") @Pattern(regexp = "^[А-ЯЁ][а-яё]*$",
            message = "Отчество должно начинаться с заглавной буквы и содержать только буквы") String middleName) {
        this.middleName = middleName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
