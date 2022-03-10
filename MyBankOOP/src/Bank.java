import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    static Scanner scanner = new Scanner(System.in);
    static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        byte op = 1;

        do {
            menu();
            op = scanner.nextByte();

            switch (op) {
                case 0 -> System.out.println("See you soon.");
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> showAccounts();
                default -> System.out.println("Insert a valid option.");

            }
        } while (op != 0);
    }

    public static void menu() {
        System.out.printf("""
                 ===============
                 1 - Create account;
                 2 - Cash deposit;
                 3 - Withdraw;
                 4 - Transfer;
                 5 - Show Accounts (Restricted Option);
                 0 - End Application.

                Choose an option:\040""");
    }

    private static void showAccounts() {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("""
                     == Account %s ==
                    %s
                    """, i + 1, accounts.get(i));

        }
    }

    public static void transfer() {
        scanner.nextLine();
        int source = checkAccount("source");
        int destination = checkAccount("destination");

        double amount = amount("transfer");
        if (accounts.get(source) == accounts.get(destination)) {
            System.out.println("You can't transfer money to yourself.");
        } else if (accounts.get(source) != accounts.get(destination)) {
            boolean transfering = Account.transfer(accounts.get(source), accounts.get(destination), amount);

            if (transfering) {
                System.out.printf("""
                        ====  Transfering R$%.2f ====
                        Source Account: %s

                        Destination Account: %s
                          """, amount, accounts.get(source), accounts.get(destination));
            } else {
                System.out.println("Unavailable balance for transfer.");
            }
        }
    }

    public static double amount(String type) {
        String op = "";

        switch (op) {
            case "balance" -> op = "see balance";
            case "deposit" -> op = "to deposit";
            case "withdraw" -> op = "to withdraw";
            case "transfer" -> op = "to transfer";
        }

        System.out.printf("Value: %s R$", op);
        double amount = scanner.nextDouble();
        while (amount < 0) {
            System.out.printf("""
                    The value %s must be greater than R$0;
                    Value %s: R$""", op, op);
            amount = scanner.nextDouble();
        }
        return amount;
    }

    public static String generateNum() {
        String number = "";
        while (number.length() != 8) {
            number = String.valueOf((int) (1 + Math.random() * 99999999));
        }
        return number;
    }

    public static int searchAccount(String number) {
        for (int i = 0; i < accounts.size(); i++) {
            if (number.equals(accounts.get(i).getNumber())) {
                return i;
            }
        }
        return -1;
    }

    public static void withdraw() {
        scanner.nextLine();
        int source = checkAccount("source");

        double amount = amount("withdraw");
        boolean withdrawing = Account.withdraw(accounts.get(source), amount);
        if (withdrawing) {
            System.out.printf("""
                     ==== Amount withdrawn R$.2f ====
                     %s
                    """, amount, accounts.get(source));
        }
    }

    public static void deposit() {
        scanner.nextLine();
        int destiny = checkAccount("destination");

        double amount = amount("deposit");
        Account.deposit(accounts.get(destiny), amount);
        System.out.printf("""
                 ==== Deposited amount R$.2f ====
                 %s
                """, amount, accounts.get(destiny));
    }

    public static void createAccount() {
        String number = generateNum();
        int registered = searchAccount(number);
        while (registered != -1) {
            number = generateNum();
            registered = searchAccount(number);
        }
        scanner.nextLine();
        System.out.print("Holder's name: ");
        String holder = scanner.nextLine();

        double amount = amount("balance");
        accounts.add(new Account(number, holder, amount));

        System.out.printf("""
                 == Account created ==
                %s
                """, accounts.get(accounts.size() - 1));

        System.out.print("\n");
    }

    public static int checkAccount(String accountNum) {
        System.out.printf("%s Account: ", accountNum);
        String account = scanner.nextLine();

        int registered = searchAccount(account);
        while (registered == -1) {
            System.out.printf("""
                    Account not registered;
                    %s Account:\040""", account, accountNum);
            account = scanner.nextLine();
            registered = searchAccount(account);
        }
        return registered;
    }
}
