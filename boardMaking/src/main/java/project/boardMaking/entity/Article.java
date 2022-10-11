package project.boardMaking.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(length = 65535, nullable = true)
    private String content;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "memberId")
    private Member member;

    /**
     * 멤버,아티클 연관관계 등록 메서드
     */
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getArticles().remove(this);
        }
        this.member = member;
        member.getArticles().add(this);
    }

    @OneToMany(mappedBy = "hashTagId", cascade = CascadeType.ALL)
    private List<HashTag> hashTags;

    //해쉬태그 추가 로직
    public void addHashTag(HashTag hashTag) {
//        서비스 로직에 저장하기
//        if (hashTags.stream().anyMatch(hashTag1 -> hashTag1.getHashTagId() == hashTag.getHashTagId())) {
//
//            //추후 비즈니스 로직으로 대체할것
//            throw new RuntimeException("이미 존재하는 해쉬태그입니다.");
//        }
        hashTags.add(hashTag);
        hashTag.setArticle(this);
    }


    @OneToMany
    @JoinColumn(name = "ArticleComment")
    private List<ArticleComment> articleComments;

    public void addArticleComment(ArticleComment articleComment) {
        articleComments.add(articleComment);
    }
}
