package org.sopt.firstweektask;

import org.sopt.firstweektask.bank.Bank;

import java.util.Scanner;

public class BankProgramMain {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("--------------------------------------------------------------");
            System.out.println("은행 프로그램 메뉴");
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. 계좌 생성");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 잔액 조회");
            System.out.println("5. 프로그램 종료");
            System.out.print("메뉴 선택: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.deposit();
                    break;
                case 3:
                    bank.withdraw();
                    break;
                case 4:
                    bank.checkBalance();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택하세요.");
            }
        } while (choice != 5);

        scanner.close();
        bank.closeScanner();
    }
}
