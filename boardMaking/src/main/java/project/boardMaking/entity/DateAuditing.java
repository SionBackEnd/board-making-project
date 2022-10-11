package project.boardMaking.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAuditing {

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedBy = LocalDateTime.now();
}
