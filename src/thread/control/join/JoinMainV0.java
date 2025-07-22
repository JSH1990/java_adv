package thread.control.join;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class JoinMainV0 {
    public static void main(String[] args) {
        log("Start");
        final Thread thread1 = new Thread(new Job(), "thread-1");
        final Thread thread2 = new Thread(new Job(), "thread-2");

        thread1.start();
        thread2.start();
        log("End");

    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("작업 시작");
            try {
                sleep(2000);
                log("작업 완료");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
