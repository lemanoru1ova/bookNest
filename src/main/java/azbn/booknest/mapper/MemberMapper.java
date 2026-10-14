package azbn.booknest.mapper;

import azbn.booknest.dto.CreateMemberRequest;
import azbn.booknest.dto.MemberResponse;
import azbn.booknest.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getFullName(),
                member.getEmail(),
                member.getPhone(),
                member.getJoinedAt(),
                member.getActive()
        );
    }

    public Member toEntity(CreateMemberRequest request) {
        return new Member(
                request.fullName(),
                request.email(),
                request.phone()
        );
    }
}