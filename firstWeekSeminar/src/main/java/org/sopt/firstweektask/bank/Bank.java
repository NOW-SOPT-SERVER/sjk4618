package org.sopt.firstweektask.bank;

import org.sopt.firstweektask.account.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private Map<String, Account> accounts;
    private Scanner scanner;

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
        while (true) {
            System.out.print("계좌번호를 입력하세요: ");
            String accountNumber = scanner.nextLine();

            if (accounts.containsKey(accountNumber)) {
                Account account = accounts.get(accountNumber);

                System.out.print("비밀번호를 입력하세요: ");
                String password = scanner.nextLine();

                if (account.checkPassword(password)) {
                    int amount = inputAmount();
                    account.deposit(amount);
                    return;
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    return;
                }
            } else {
                System.out.println("해당 계좌가 존재하지 않습니다. 다시 입력하세요.");
            }
        }
    }

    public void withdraw() {
        while (true) {
            System.out.print("계좌번호를 입력하세요: ");
            String accountNumber = scanner.nextLine();

            if (accounts.containsKey(accountNumber)) {
                Account account = accounts.get(accountNumber);

                System.out.print("비밀번호를 입력하세요: ");
                String password = scanner.nextLine();

                if (account.checkPassword(password)) {
                    int amount = inputAmount();
                    account.withdraw(amount);
                    return;
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    return;
                }
            } else {
                System.out.println("해당 계좌가 존재하지 않습니다. 다시 입력하세요.");
            }
        }
    }

    public void transfer() {
        while (true) {
            System.out.print("송금할 계좌번호를 입력하세요: ");
            String senderAccountNumber = scanner.nextLine();

            if (accounts.containsKey(senderAccountNumber)) {   //보내는 계좌번호 입력이 맞다면 실행
                Account senderAccount = accounts.get(senderAccountNumber);

                System.out.print("비밀번호를 입력하세요: ");
                String password = scanner.nextLine();

                if (senderAccount.checkPassword(password)) {   //보내는 계좌번호의 비번이 맞다면 실행
                    System.out.print("수취인 계좌번호를 입력하세요: ");
                    String recipientAccountNumber = scanner.nextLine();

                    while (!accounts.containsKey(recipientAccountNumber)) { //수취인 계좌번호가 틀리면 계속 받아옴
                        System.out.print("없는 계좌번호입니다. 수취인 계좌번호를 다시 입력하세요: ");
                        recipientAccountNumber = scanner.nextLine();
                    }
                    //수취인 계좌번호가 맞으면 실행
                    Account recipientAccount = accounts.get(recipientAccountNumber);
                    int amount = inputAmount();
                    senderAccount.transfer(recipientAccount, amount);
                    return;
                } else {    //보내는 계좌번호의 비번이 틀리다면 실행
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    return;
                }
            } else {    //보내는 계좌번호가 틀리다면 실행
                System.out.println("해당 계좌가 존재하지 않습니다. 다시 입력하세요.");
            }


//            Account senderAccount = accounts.get(senderAccountNumber);
//            if (senderAccount != null) {
//                System.out.print("비밀번호를 입력하세요: ");
//                String password = scanner.nextLine();
//                if (senderAccount.checkPassword(password)) {
//                    System.out.print("수취인 계좌번호를 입력하세요: ");
//                    String recipientAccountNumber = scanner.nextLine();
//                    Account recipientAccount = accounts.get(recipientAccountNumber);
//                    if (recipientAccount != null) {
//                        int amount = inputAmount();
//                        senderAccount.transfer(recipientAccount, amount);
//                        return;
//                    } else {
//                        System.out.println("수취인 계좌가 존재하지 않습니다. 다시 입력하세요.");
//                    }
//                } else {
//                    System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요.");
//                }
//            } else {
//                System.out.println("해당 계좌가 존재하지 않습니다. 다시 입력하세요.");
//            }
        }
    }

    public void checkBalance() {
        while (true) {
            System.out.print("계좌번호를 입력하세요: ");
            String accountNumber = scanner.nextLine();

            if (accounts.containsKey(accountNumber)) {
                Account account = accounts.get(accountNumber);

                System.out.print("비밀번호를 입력하세요: ");
                String password = scanner.nextLine();

                if (account.checkPassword(password)) {
                    System.out.println("잔액은 " + account.getBalance() + "원 입니다.");
                    return;
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    return;
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
                if (amount <= 0) {
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

