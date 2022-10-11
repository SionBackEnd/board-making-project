package project.boardMaking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.boardMaking.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

}
