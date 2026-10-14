package azbn.booknest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateLoanRequest(

        @NotNull(message = "Kitab ID bos ola bilmez")
        @Positive
        Long bookId,

        @NotNull(message = "Uzv ID bos ola bilmez")
        @Positive
        Long memberId,

        @Positive(message = "Gun sayi musbet olmalidi")
        Integer loanDays
) {
}