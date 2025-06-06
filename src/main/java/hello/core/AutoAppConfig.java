package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // @ComponentScan : 자동으로 스프링 빈으로 끌어올려준다.
    // excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
    // excludeFilter : 자동으로 AppConfig 스캔을 막기 위해서 설정한 내용(기존 예제 코드를 남기기 위한 코드임, 실무에선 안그럼)
    // @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록, 이름은 앞글자만 소문자로 변경해서 클래스 명을 사용
    // 이름을 지정하고 싶으면 @Component("name") 형식
    // 생성자에 @Autowired를 지정하면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입
    // 기본 조회 전략은 타입이 같은 빈을 찾아서 주입(getBean(MemberRepository.class)와 같다.)
    // 생성자에 파라미터가 많아도 다 찾아서 자동으로 주입
    // 사용 클래스 : RateDiscountPolicy,MemoryMemberRepository, MemberServiceImpl, OrderServiceImpl
}
