package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//일단 DB말고 메모리로 데이터 저장하는 방법을 선택, 후에 implemets로 다른 DB 생성가능
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();//실무에서는 공유되는 객체일 때는 동시성문제 때문에 Concurrency로 써야함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//만약 null이 반환되면 optional로 감싸서 전달해줌. client에서 처리가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        //store에 있는 value 중에 name(member)이 name(parameter)과 같은 member를 아무거나 찾아서 반환
        //findAny는 null이면 자동으로 optional로 반환
        //stream은 배열과 컬렉션을 함수형으로 처리가능하도록 해줌
    }

    @Override
    public List<Member> findAll() {
        //store의 value인 member들이 쭉 반환됨
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear(); //스토어를 싹 비움
    }
}
