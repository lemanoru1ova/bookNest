package azbn.booknest.service;

import azbn.booknest.dto.CreateMemberRequest;
import azbn.booknest.dto.MemberResponse;

import java.util.List;

public interface MemberService {

    List<MemberResponse> findAll();

    MemberResponse findById(Long id);

    MemberResponse create(CreateMemberRequest request);

    List<MemberResponse> search(String keyword);

    void deactivate(Long id);
}