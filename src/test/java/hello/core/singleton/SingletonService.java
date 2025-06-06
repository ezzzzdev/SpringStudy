package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

    // 싱글톤은 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
    // 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야한다.

    // 본인 클래스 인스턴스를 이렇게 만듬
    // 직접 인스턴스 생성을 막는것
    private static final SingletonService instance = new SingletonService();

    // 자기 자신 인스턴스를 생성하여 리턴
    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private으로 설정하여 new로 인스턴스 생성 막음
    private SingletonService(){}
    
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }


    
}
