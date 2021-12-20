package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {//cmd+shift+t → 클래스에 해당하는 테스트 자동 생성
    //private final MemberRepository memberRepository = new MemoryMemberRepository();//테스트 코드에서 두개가 생기는 걸 방지하기 위해 이건 안씀
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //MemberService가 직접 new해서 필드를 넣어주지 않고 외부에서 값을 넣어줌.//DI 의존성역전
    }//contructor를 넣어서 테스트에서 memberRepository를 넣어주게 만듦

    //회원가입
    //요구사항 : 같은 이름의 중복회원은 안된다.
    public Long join(Member member){
        //cmd+opt+v 하면 자동 생성
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {//만약 result에 값(m)이 있으면
//            throw new IllegalStateException("이미 존재하는 이름입니다.");//exeption 떤짐
//
//        });
        //위의 코드는 안예쁨
        //근데 findByName해서 쭉 로직이 나오기 때문에 이건 method로 따로 뽑는게 좋음
        //단축키 : ctr+t
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())//어차피 optional로 return되기 때문에 ifPresent 바로쓸 수 있음
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //id로 회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
