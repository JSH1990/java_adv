package thread.sync;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class BankMain {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccountV1(1000);
        WithdrawTask task1 = new WithdrawTask(account, 500);
        WithdrawTask task2 = new WithdrawTask(account, 300);
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
        
        sleep(500);
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());
        
        t1.join();
        t2.join();
        log("최종잔액: " + account.getBanlance());
            
    }
}