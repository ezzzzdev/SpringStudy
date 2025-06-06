package hello.core.singleton;

public class StatefulService {
    // 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다
    // 무상태(stateless)로 설계
    // 1. 특정 클라이언트에 의존적인 필드가 있으면 안된다
    // 2. 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다
    // 3. 가급적 읽기만 가능해야함.
    // 4. 필드 대신 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 함.

//    private int price; // 상태를 유지하는 필드

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
//        this.price = price;
        return price;
    }

//    public int getPrice(){
//        return price;
//    }

}
