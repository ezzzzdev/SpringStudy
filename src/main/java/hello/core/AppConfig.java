package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 앱 전체를 설정 및 구성
// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
// AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입(연결)해준다 = 의존성 주입(DI)
// 이를 통해 생성자는 Config가 주입, Impl들은 실행에 집중하면 된다.
public class AppConfig {

    // AppConfig를 통해 memberService를 사용하면
    public MemberService memberService(){
        return new MemberServiceImpl(MemberRepository());
    }

    private MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
