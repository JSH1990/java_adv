package thread.bounded;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedQueueV3 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();

    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐에 가득 참, 생산자 대기");
            try {
                wait(); //Runable -> Waiting, 락 반납
                log("[put] 생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] 생산자 데이터 저장, notify() 호출");
        notify(); //대기 스레드, Wait -> Blocked
        //notifyAll(); 모든 대기 스레드, Wait -> Blocked
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터 없음, 소비자 대기");
            try {
                wait();
                log("[take] 소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] 소비자 데이터 소비, notify() 호출");
        notify(); //대기 스레드, Wait -> Blocked
        //notifyAll(); 모든 대기 스레드, Wait -> Blocked
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}