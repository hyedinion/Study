package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();//근데 이건 memberService안에 있는 memberRepository와 중복됨.
                                                                            //두개가 달라지면 문제가 생김
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){// 이렇게 하면 테스트를 할때마다 각각 생성해줌
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    } //MemberService의 입장에서 보면 memberRepository 필드를 외부에서 넣어주는거임.
        // 이게바로 DI 의존성 역전

    @AfterEach // 모든 메소드(테스트)가 끝날 때마다 호출해서 실행함
    public void afterEach(){
        memberRepository.clearStore();
    }

    //테스트 코드는 한글로 적어도 됨, build과정에 들어가지 않기 때문
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    } //사실 이거는 너무 단순함. testCase는 정상 flow도 중요하지만 예외 flow가 훨씬 중요함

    @Test
    void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //이 join을 실행하면 예외가 터져야 한다.
        //assertThrows(NullPointerException.class, ()->memberService.join(member2)); //이건 실패
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try{ //try catch문은 너무 복잡, 위의 코드로 변경
//            memberService.join(member2);
//            fail("예외가 발생했습니다.");
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}