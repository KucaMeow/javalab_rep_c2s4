package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class RegisterDto {
    @Email(message = "{errors.incorrect.email}")
    String email;
    @NotNull(message = "{errors.null.password}")
    @Size(min = 3, max = 16, message = "{errors.incorrect.password.size}")
    String password;
}
