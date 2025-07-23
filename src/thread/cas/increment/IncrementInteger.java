package thread.cas.increment;

//값을 증가하는 기능을 가진 인터페이스
public interface IncrementInteger {
    //값을 하나 증가
    void increment();

    //값을 조회
    int get();
}
