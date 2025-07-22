package thread.sync;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class BankAccountV1 implements BankAccount{

    //계좌의 잔액
    private int balance;

    public BankAccountV1(int initialBalance){
        this.balance = initialBalance;
    }

    //계좌의 돈을 출금
    @Override
    public synchronized boolean withdraw(int amount) throws InterruptedException {
        log("거래 시작: " + getClass().getSimpleName());

        log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
        if(balance < amount){
            log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
            return false;
        }

        log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
        sleep(1000);
        balance = balance - amount;
        log("[출금 완료] 출금액: " + amount + ", 변경 잔액: " + balance);
        log("거래 종료");
        return true;
    }

    //계좌의 잔액을 반환
    @Override
    public int getBanlance() {
        return balance;
    }   
}