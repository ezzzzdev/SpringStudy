package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // 테스트를 할 떄 이렇게 하는건 별로다
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        // soutv로 입력 = 괄호 안에 변수까지 나옴(데이터 찍어볼때 유용)
        System.out.println("find Member = " + findMember.getName());
        System.out.println("new member = " + member.getName());
    }
}
