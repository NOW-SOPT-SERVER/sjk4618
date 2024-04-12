package org.sopt.firstweektask.account;


//인터페이스 내의 메서드는 모두 public abstract이라 생략 가능하지만, 공부중이니 적어봄, (자바8부터 default 메소드와 static 메소드 사용가능)
public interface AccountInterface {
    public abstract String getAccountNumber();
    public abstract boolean checkPassword(String inputPassword);
    public abstract void deposit(int amount);
    public abstract void withdraw(int amount);
    public abstract void transfer(AccountInterface recipient, int amount);
    public abstract int getBalance();
}
