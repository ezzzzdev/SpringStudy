package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 가입을 하고 회원을 찾으려면 Repo가 필요 + null이면 안되므로 구현 객체 -> 다형성에 의해 MemoryMemberRepository에 있는 save가 호출됨
    private final MemberRepository memberRepository;

    // 생성자를 통해 기존에 memoryMemberRepository를 들어가는걸 생성자를 통해 들어가도록 함
    // 생성자에 의존성 주입을 자동으로 해주도록 설정(AutoAppConfig)
    @Autowired // ac.getBean(MemberRepository.class)랑 같은 효과)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
