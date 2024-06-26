package org.sopt.firstweektask.account;

public class Account implements AccountInterface {
    private String name;
    private String accountNumber;
    private int balance;
    private String password;
    private final static int MINIMUM_AMOUNT = 0;


    public Account(String name, String accountNumber, String password) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = MINIMUM_AMOUNT;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public void deposit(int amount) {
        if (amount <= MINIMUM_AMOUNT) {
            System.out.println("입금할 금액은 0원보다 커야 합니다.");
            return;
        }
        balance += amount;
        System.out.println(amount + "원이 입금되었습니다.");
        printRemainBalance(balance);
    }

    public void withdraw(int amount) {
        if (amount <= MINIMUM_AMOUNT) {
            System.out.println("출금할 금액은 0원보다 커야 합니다.");
            return;
        }
        if (balance < amount) {
            System.out.println("잔액이 부족합니다.");
            return;
        }
        balance -= amount;
        System.out.println(amount + "원이 출금되었습니다.");
        printRemainBalance(balance);
    }

    public void transfer(AccountInterface recipient, int amount) {
        if (amount <= MINIMUM_AMOUNT) {
            System.out.println("이체할 금액은 0원보다 커야 합니다.");
            return;
        }
        if (balance < amount) {
            System.out.println("잔액이 부족합니다.");
            printRemainBalance(balance);
            return;
        }
        balance -= amount;
        recipient.deposit(amount);
        System.out.println(amount + "원을 " + ((Account)recipient).getName() + "님에게 이체했습니다.");
        printRemainBalance(balance);
    }
    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    private void printRemainBalance(int balance) {
        System.out.println("현재 잔액은 " + balance + "원 입니다.");
    }
}

