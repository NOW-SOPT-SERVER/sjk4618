package org.sopt.firstweektask.bank;

import org.sopt.firstweektask.account.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank implements BankInterface {
    private Map<String, Account> accounts;
    private Scanner scanner;

    private final static int MINIMUM_AMOUNT = 0;

    public Bank() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();

        System.out.print("계좌번호를 입력하세요: ");
        String accountNumber = scanner.nextLine();
        //이미 있는 계좌번호면 다시 입력받기
        while (accounts.containsKey(accountNumber)) {
            System.out.println("이미 존재하는 계좌번호입니다. 다른 계좌번호를 입력하세요.");
            System.out.print("계좌번호를 다시 입력하세요: ");
            accountNumber = scanner.nextLine();
        }

        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        Account account = new Account(name, accountNumber, password);
        accounts.put(accountNumber, account);
        System.out.println("계좌가 생성되었습니다.");

    }

    public void deposit() {
        Account account = checkAccountAndPwd();
        if (account != null) {
            int amount = inputAmount();
            account.deposit(amount);
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }

    public void withdraw() {
        Account account = checkAccountAndPwd();
        if (account != null) {
            int amount = inputAmount();
            account.withdraw(amount);
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }

    public void transfer() {

        Account senderAccount = checkAccountAndPwd();

        if (senderAccount != null) {
            System.out.print("수취인 계좌번호를 입력하세요: ");
            String recipientAccountNumber = scanner.nextLine();

            while (!accounts.containsKey(recipientAccountNumber) || (senderAccount.getAccountNumber().equals(recipientAccountNumber))) { //수취인 계좌번호가 틀리면 계속 받아옴
                System.out.print("없는 계좌번호거나 자신의 계좌번호입니다. 수취인 계좌번호를 다시 입력하세요: ");
                recipientAccountNumber = scanner.nextLine();
            }
            //수취인 계좌번호가 맞으면 실행
            Account recipientAccount = accounts.get(recipientAccountNumber);
            int amount = inputAmount();
            senderAccount.transfer(recipientAccount, amount);
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }

    public void checkBalance() {
        Account account = checkAccountAndPwd();
        if (account != null) {
            System.out.println("잔액은 " + account.getBalance() + "원 입니다.");
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }

    private Account checkAccountNumber(String accountNumber) {
        // getOrDefault : 찾는 키가 존재한다면 찾는 키의 값을 반환하고 없다면 기본 값을 반환하는 메서드
        return accounts.getOrDefault(accountNumber, null);
    }

    private Account checkAccountAndPwd() {
        while (true) {
            System.out.print("계좌번호를 입력하세요: ");
            String accountNumber = scanner.nextLine();

            Account account = checkAccountNumber(accountNumber);

            if (account != null) {
                System.out.print("비밀번호를 입력하세요: ");
                String password = scanner.nextLine();

                if (account.checkPassword(password)) {
                    return account;
                } else {
                    return null;
                }
            } else {
                System.out.println("해당 계좌가 존재하지 않습니다. 다시 입력하세요.");
            }
        }
    }


    private int inputAmount() {
        while (true) {
            System.out.print("금액을 입력하세요: ");
            String amountStr = scanner.nextLine();
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount <= MINIMUM_AMOUNT) {
                    System.out.println("금액은 0원보다 커야 합니다.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("정수로 입력하세요.");
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}

