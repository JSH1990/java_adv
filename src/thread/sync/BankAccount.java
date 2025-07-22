package thread.sync;

public interface BankAccount {

    //계좌의 돈을 출금
    boolean withdraw(int amount) throws InterruptedException;

    //계좌의 잔액을 반환
    int getBanlance();
}