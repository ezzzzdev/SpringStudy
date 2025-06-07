package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

//        ac.getBean("beanB", BeanB.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));
    }

    // @FilterType 옵션
    // 1. Annotation : 기본값, 어노테이션을 인식해서 동작(org.example.SomeAnnotation)
    // 2. Assignable_type : 지정한 타입과 자식 타입을 인식해서 동작(org.example.SomeClass)
    // 3. AspectJ : AspectJ 패턴 사용(org.example..*Service+)
    // 4. Regex : 정규 표현식(org\.example\.Defailt.*)
    // 5. Custom : TypeFilter라는 인터페이스를 구현해서 처리(org.example.MyTypeFilter)
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(classes = MyIncludeComponent.class),
            excludeFilters = @Filter(classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
