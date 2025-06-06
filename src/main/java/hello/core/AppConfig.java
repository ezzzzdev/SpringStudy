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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 앱 전체를 설정 및 구성
// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성
// AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입(연결)해준다 = 의존성 주입(DI)
// 이를 통해 생성자는 Config가 주입, Impl들은 실행에 집중하면 된다.
@Configuration
public class AppConfig {


    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 이러면 싱글톤이 깨지는거 아닌가? -> ConfigurationSingletonTest
    // 결론은 안깨짐
    
    // call 순서?
    // memberService - memberRepository * 2 -> orderService -> memberRepository (예상)
    // memberService - memberRepository -> orderService (?)

    // why?
    // @Configuration 때문이다.
    // @Configuration이 만약 사라지고 @Bean만 남긴다면??
    // 순수 AppConfig가 그대로 호출된다 -> CGLIB이 호출된게 아님
    // 추가로 원래 예상했던 call 순서대로 불려오면서 싱글톤이 깨진게 보임(순수한 Java코드로 보임)
    // 추가로 스프링 컨테이너의 관리를 받지 않기 때문에 impl에 있는 내용들을 직접적으로 호출하는 것과 같다.
    // 아래와 같이 @Configuration처럼 직접 의존성을 주입하는 방법도 있긴 함.
//    @Autowired MemberRepository memberRepository;

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
