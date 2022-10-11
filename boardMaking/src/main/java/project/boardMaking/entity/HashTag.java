package project.boardMaking.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTag extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashTagId;

    //동일한 해쉬테그가 존재한다면 생성하지않는다. 유니크 옵션 추가
    @Column(length = 100, nullable = false, unique = true)
    private String content;

    //해쉬태그를 이용하여 관련 게시판들을 찾기위해 양방향 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;

    public void setArticle(Article article) {
        this.article = article;
    }
}
