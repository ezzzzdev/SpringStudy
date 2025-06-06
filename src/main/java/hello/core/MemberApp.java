package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    // 테스트를 할 떄 이렇게 하는건 별로다
    public static void main(String[] args) {
        // Appconfig 없을때
//        MemberService memberService = new MemberServiceImpl();
        // Appconfig 있을 때
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // AppConfig 클래스의 @Configuration의 @Bean을 찾아와서 객체화
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // (이름, 리턴 타입)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        // soutv로 입력 = 괄호 안에 변수까지 나옴(데이터 찍어볼때 유용)
        System.out.println("find Member = " + findMember.getName());
        System.out.println("new member = " + member.getName());
    }
}
