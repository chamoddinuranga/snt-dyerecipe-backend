package lk.snt.dyeBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userName;
    private String password;
    private String role; // e.g., "ADMIN", "USER"
}
