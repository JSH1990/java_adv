package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoundedQueueV6_1 implements BoundedQueue {
    private BlockingQueue<String> queue;

    public BoundedQueueV6_1(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    
    @Override
    public void put(String data) {
      queue.add(data);
    }

    @Override
    public String take() {
        return queue.remove();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
