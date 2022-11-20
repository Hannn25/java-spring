package net.batch6.apiLibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String phoneNumber;
}
