package project.boardMaking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
public class MemberDto {

    @Getter
    public static class Post {
        @Email
        private String email;
        @NotBlank
        private String name;
        @NotBlank
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;
        @NotBlank
        private String nickName;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {
        private Long memberId;
        private String email;
        private String name;
        private String nickname;
        private String phone;
    }
}
