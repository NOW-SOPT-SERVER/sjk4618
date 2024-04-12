package org.sopt.firstweektask.bank;

import org.sopt.firstweektask.account.AccountInterface;

//인터페이스 내의 메서드는 모두 public abstract이라 생략 가능하지만, 공부중이니 적어봄, (자바8부터 default 메소드와 static 메소드 사용가능)
public interface BankInterface {
    public abstract void createAccount();
    public abstract void deposit();
    public abstract void withdraw();
    public abstract void transfer();
    public abstract void checkBalance();
    public abstract void closeScanner();
}
