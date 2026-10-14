package azbn.booknest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(

        @NotBlank(message = "Kateqoriya adı bos ola bilmez")
        @Size(min = 2, max = 100)
        String name
) {
}