package project.boardMaking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.bind.annotation.Mapping;
import project.boardMaking.dto.MemberDto;
import project.boardMaking.entity.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    default Member PostToMember(MemberDto.Post post) {
        Member member = new Member(
                post.getEmail(),
                post.getName(),
                post.getPhone(),
                post.getNickName()
        );
        return member;
    }

    default MemberDto.Response MemberToResponse(Member member) {
        MemberDto.Response response = new MemberDto.Response(
                member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone(),
                member.getNickname()
        );
        return response;
    }
}
