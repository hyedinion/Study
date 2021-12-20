package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//아직 DB가 결정되지 않았다는 요구사항이 있기 때문에 interface로 생성
public interface MemberRepository {//데이터베이스에 접근, Model에 해당, Model로 어떤기능을 할지 interface로 구현
    Member save(Member member);//회원 저장기능
    //optional은 null처리하는 방법중 하나임
    Optional<Member> findById(Long id); //id값으로 회원찾기
    Optional<Member> findByName(String name); //name으로 회원찾기
    List<Member> findAll();//다 조회하기
}
