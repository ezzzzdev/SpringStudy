package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 이렇게하면 DIP가 위반된다(인터페이스와 구체화 모두에 의존)
    // 고정값과 비율 할인을 변경하려면 직접 코드를 만져야하므로 OCP 위반
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
 
    // DIP 위반 해결? -> DiscountPolicy의 구현 객체를 누군가 대신 생성하고 주입
//    private DiscountPolicy discountPolicy;

    // 해결(생성자 주입을 위해 분리하는 방법)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // grade를 넘길지 member를 넘길지는 상황에 맞추어 사용
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
