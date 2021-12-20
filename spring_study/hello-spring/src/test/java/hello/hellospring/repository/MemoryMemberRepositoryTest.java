package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 모든 메소드(테스트)가 끝날 때마다 호출해서 실행함
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        //optional이기 때문에 끝에 get을 붙혀준거임
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result==member));
        //위의 코드말고 junit에서 제공해주는 assertion이 있음
        //Assertions.assertEquals(member,result);
        //Assertions.assertEquals(member,null); //만약에 같지 않으면 빨간색으로 test failed라고 나타내줌
        assertThat(member).isEqualTo(result);//assertj에서 제공해주는 assertion, member가 result랑 똑같냐를 확인해줌
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //Optional<Member> result = repository.findByName("spring1"); //get 사용안하면 optional로 감싸줘야함
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
