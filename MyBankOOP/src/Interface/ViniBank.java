package Interface;

import java.util.Scanner;

import Logic.Bank;

public class ViniBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank viniBank = new Bank();

        byte op = 0;

        do {
            menu();
            System.out.print("\nChoose an option: ");
            op = scanner.nextByte();
            switch (op) {
                case 0:
                    System.out.println("   See you soon!");
                    break;
                case 1:
                    int number = viniBank.createAccount();
                    System.out.println("Your account has been created: " + number);
                    break;
                case 2:
                    System.out.print("Insert your account number: ");
                    number = scanner.nextInt();
                    if (viniBank.searchNumber(number) == -1) {
                        System.out.println("This account does not exist.");
                        break;
                    }
                    System.out.print("How much do you want to deposit? ");
                    double deposit = scanner.nextInt();
                    if (deposit <= 0) {
                        System.out.println("You cannot deposit amounts less than or equal to zero.");
                        break;
                    }
                    viniBank.deposit(number, deposit);
                    System.out.println("Done.");
                    break;
                case 3:
                    System.out.print("Insert your account number: ");
                    number = scanner.nextInt();
                    if(viniBank.searchNumber(number) == -1) {
                        System.out.println("This account does not exist.");
                        break;
                    }
                    System.out.print("How much do you want to withdraw? ");
                    double withdraw = scanner.nextInt();
                    viniBank.withdraw(number, withdraw);
                    System.out.println("Done.");
                    break;
                case 4:
                    System.out.print("Insert your account number: ");
                    int source = scanner.nextInt();
                    if(viniBank.searchNumber(source) == -1) {
                        System.out.println("This account does not exist.");
                        break;
                    }
                    System.out.print("Insert destination account: ");
                    int destination = scanner.nextInt();
                    if(viniBank.searchNumber(destination) == -1) {
                        System.out.println("This account does not exist.");
                        break;
                    }
                    if (source == destination) {
                        System.out.println("You can't transfer money to yourself.");
                        break;
                    }
                    System.out.print("What is the amount to be transferred?");
                    double value = scanner.nextDouble();
                    viniBank.transfer(source, destination, value);
                    break;
                case 5:
                    System.out.print("Insert your account number: ");
                    number = scanner.nextInt();
                    value = viniBank.checkBalance(number);
                    System.out.println("Your balance: " + value);
                    break;
                case 6: 
                    System.out.println("Accounts:");
                    viniBank.showAccounts();
                default:
                    System.out.println("Select a valid option.");
                    break;
            }
            System.out.println("");
        } while (op != 0);
    }

    public static void menu() {
        System.out.println("+----------------------+");
        System.out.println("|                      |");
        System.out.println("|       VINIBANK       |");
        System.out.println("|                      |");
        System.out.println("+----------------------+");
        System.out.println("");
        System.out.println("  1 - Create Account");
        System.out.println("  2 - Deposit;");
        System.out.println("  3 - Withdraw;");
        System.out.println("  4 - Transfer;");
        System.out.println("  5 - Check Balance;");
        System.out.println("  6 - Show Accounts (Restricted Option);");
        System.out.println("  0 - Exit.");
    }
}
