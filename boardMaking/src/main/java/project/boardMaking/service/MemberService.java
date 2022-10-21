package project.boardMaking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.boardMaking.entity.Member;
import project.boardMaking.repository.MemberRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        verifiedMember(member);

        Member saveMember = memberRepository.save(member);

        return saveMember;
    }



    public void verifiedMember(Member member) {
        if (memberRepository.findByNickname(member.getNickname()).isPresent()) {
            throw new RuntimeException("이미 존재하는 닉네임입니다. 다른 닉넴임을 입력해주세요.");
        }
        if(memberRepository.findByEmail(member.getEmail()).isPresent())
            throw new RuntimeException("이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요.");
    }

}
