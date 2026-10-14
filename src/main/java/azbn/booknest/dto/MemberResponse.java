package azbn.booknest.dto;

import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        LocalDate joinedAt,
        Boolean active
) {
}