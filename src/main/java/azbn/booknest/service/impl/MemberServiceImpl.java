package azbn.booknest.service.impl;

import azbn.booknest.dto.CreateMemberRequest;
import azbn.booknest.dto.MemberResponse;
import azbn.booknest.entity.Member;
import azbn.booknest.exception.DuplicateEmailException;
import azbn.booknest.exception.MemberNotFoundException;
import azbn.booknest.mapper.MemberMapper;
import azbn.booknest.repository.MemberRepository;
import azbn.booknest.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepository,
                             MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toResponse)
                .toList();
    }

    @Override
    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        return memberMapper.toResponse(member);
    }

    @Override
    @Transactional
    public MemberResponse create(CreateMemberRequest request) {
        if (memberRepository.existsByEmailIgnoreCase(request.email())) {
            throw new DuplicateEmailException(request.email());
        }
        Member saved = memberRepository.save(memberMapper.toEntity(request));
        return memberMapper.toResponse(saved);
    }

    @Override
    public List<MemberResponse> search(String keyword) {
        return memberRepository.findByFullNameContainingIgnoreCase(keyword).stream()
                .map(memberMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deactivate(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.setActive(false);
    }
}