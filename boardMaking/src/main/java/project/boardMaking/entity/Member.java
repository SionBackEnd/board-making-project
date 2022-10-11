package project.boardMaking.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(length = 20, nullable = false, unique = true)
    private String phone;
    @Column(length = 30, nullable = false)
    private String nickname;

    //Member 생성자
    public Member(String email, String name, String phone, String nickname) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
    }

    @OneToMany(mappedBy = "member")
    private List<Article> articles;


}
