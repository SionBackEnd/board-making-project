package project.boardMaking.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends DateAuditing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(length = 100,nullable = false,unique = true)
    private String email;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String password;
    @Column(length = 20, nullable = false, unique = true)
    private String phone;
    @Column(length = 30, nullable = false)
    private String nickname;

    @ElementCollection
    private List<String> roles;

    public void changeRole(List<String> roles) {
        this.roles = roles;
    }

    //Member 생성자
    @Builder
    public Member(Long memberId, String email, String name, String password,String phone, String nickname) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.nickname = nickname;
    }

    @OneToMany(mappedBy = "member")
    private List<Article> articles;


    public void encodePassword(String encode) {
        this.password = encode;
    }
}
