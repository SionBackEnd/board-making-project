package project.boardMaking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.boardMaking.auth.utils.CustomAuthorityUtils;
import project.boardMaking.entity.Member;
import project.boardMaking.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final CustomAuthorityUtils authorityUtils;


    public Member createMember(Member member) {
        verifiedMember(member);
        //패스워드 암호화
        member.encodePassword(bCryptPasswordEncoder.encode(member.getPassword()));
        //권한 설정
        member.changeRole(authorityUtils.createRoles(member.getEmail()));

        Member saveMember = memberRepository.save(member);

        return saveMember;
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException());
    }



    public void verifiedMember(Member member) {
        if (memberRepository.findByNickname(member.getNickname()).isPresent()) {
            throw new RuntimeException("이미 존재하는 닉네임입니다. 다른 닉넴임을 입력해주세요.");
        }
        if(memberRepository.findByEmail(member.getEmail()).isPresent())
            throw new RuntimeException("이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요.");
    }

}
