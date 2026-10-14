package azbn.booknest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateMemberRequest(

        @NotBlank(message = "Ad boş ola bilmez")
        @Size(min = 2, max = 100)
        String fullName,

        @NotBlank(message = "Email boş ola bilmez")
        @Email(message = "Duzgun email formatinda daxil eliyin")
        @Size(max = 150)
        String email,

        @Size(max = 20)
        String phone
) {
}