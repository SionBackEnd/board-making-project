package project.boardMaking.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public abstract class Auditing extends DateAuditing{

    @Column(nullable = false, updatable = false)
    private String createdWho;
    @Column(nullable = false)
    private String modifiedByWho;

}
