package project.boardMaking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.bind.annotation.Mapping;
import project.boardMaking.dto.MemberDto;
import project.boardMaking.entity.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    default Member PostToMember(MemberDto.Post post) {
        Member member = Member.builder()
                .email(post.getEmail())
                .name(post.getName())
                .password(post.getPassword())
                .phone(post.getPhone())
                .nickname(post.getNickName())
                .build();

        return member;
    }

    default MemberDto.Response MemberToResponse(Member member) {
        MemberDto.Response response = MemberDto.Response.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .phone(member.getPhone())
                .nickname(member.getNickname())
                .roles(member.getRoles())
                .build();

        return response;
    }
}
