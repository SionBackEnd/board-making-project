package project.boardMaking.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleComment extends Auditing{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleCommentId;

    @Column(length = 2000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleID")
    private Article article;
}
