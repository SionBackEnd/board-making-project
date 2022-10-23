package project.boardMaking.auth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.boardMaking.auth.utils.CustomAuthorityUtils;
import project.boardMaking.entity.Member;
import project.boardMaking.repository.MemberRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * - Custom UserDetails 사용
 * - User Role 을 DB 에서 조회한 후, HelloAuthorityUtils 로 Spring Security 에게 Role 정보 제공
 */
@Service
public class HelloUserDetailsServiceV3 implements UserDetailsService {
    //중요하게 기억해야하는 부분은 시큐리티 설정에서 loginProcessingURL("/process_login")
    //login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC 되어 있는 loadUserByUsername 메서드가 실행된다.
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public HelloUserDetailsServiceV3(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new RuntimeException("해당 유저가 없습니다."));

        return new HelloUserDetails(findMember);
    }

    private final class HelloUserDetails implements UserDetails {
        Member member;
        HelloUserDetails(Member findMember) {
            member = Member.builder()
                    .memberId(findMember.getMemberId())
                    .email(findMember.getEmail())
                    .name(findMember.getName())
                    .password(findMember.getPassword())
                    .phone(findMember.getPhone())
                    .nickname(findMember.getNickname())
                    .roles(findMember.getRoles())
                    .build();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            System.out.println("USER Role 정보는 DB에 있는걸로 제공");
            //원래 이곳에는 유저의 권한을 리턴하는 곳인데 authorityUtils 클래스안에 권한을 생성하는 로직이 있어서
            //그 로직을 이요해서 권한을 리턴하는 형식을 띄우고 있다.
            //만약 모든회원의 권한을 USER 로 통일한다면, 그냥 return GrantedAuthority 타입으로 유저권한을 담아서 리턴하면 된다.
            return authorityUtils.createAuthorities(member.getRoles());
        }

        @Override
        public String getPassword() {
            return member.getPassword();
        }

        @Override
        public String getUsername() {
            return member.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
