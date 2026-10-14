package azbn.booknest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorRequest(

        @NotBlank(message = "Author adi bos ola bilmez")
        @Size(min = 2, max = 150)
        String name,

        @Size(max = 100)
        String country
) {
}