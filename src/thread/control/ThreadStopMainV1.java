package thread.control;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class ThreadStopMainV1 {
    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(1000);
        log("작업 중단 지시 runFlag = false");
        task.runFlag = false;
    }

static class MyTask implements Runnable {
    volatile boolean runFlag = true;

    @Override
    public void run() {
        while (runFlag) {
            log("작업 중");
            try {
                sleep(3000);
                log("자원 정리");
                log("작업 종료");
            } catch (InterruptedException e) {
               
            }
        }
    }
}
}