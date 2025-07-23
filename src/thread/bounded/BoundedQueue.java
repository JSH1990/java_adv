package thread.bounded;

//버퍼 역할을 하는 큐의 인터페이스스
public interface BoundedQueue {
    //버퍼에 데이터를 보관(생산자)
    void put(String data);

    //버퍼에서 데이터를 꺼냄(소비자)
    String take();
}