package thread.executor;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class RunnableTask implements Runnable {
    private final String name;
    private int sleepMs = 1000;

    public RunnableTask(String name) {
        this.name = name;
    }

    public RunnableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public void run() {
        log(name + " 시작");
        try {
            sleep(sleepMs);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 작업 시간 시뮬레이션
        log(name + " 완료");
    }
}
