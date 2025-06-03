package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 가입을 하고 회원을 찾으려면 Repo가 필요 + null이면 안되므로 구현 객체 -> 다형성에 의해 MemoryMemberRepository에 있는 save가 호출됨
    private final MemberRepository memberRepository;

    // 생성자를 통해 기존에 memoryMemberRepository를 들어가는걸 생성자를 통해 들어가도록 함
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
}
