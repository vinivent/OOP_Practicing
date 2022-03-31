package Logic;

import java.util.LinkedList;

public class Bank {
    private static LinkedList<Account> accounts = new LinkedList<Account>();

    public int createAccount() {
        int accountNumber = ((int) (1 + Math.random() * 99999999));
        Account account = new Account(accountNumber);
        accounts.add(account);

        return accountNumber;
    }

    public void withdraw(int accountNumber, double value) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                account.withdraw(value);
            }
        }
    }

    public void deposit(int accountNumber, double value) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                account.deposit(value);
            }
        }
    }

    public void transfer(int sourceAccount, int destinationAccount, double value) {
        for (Account account : accounts) {
            if (account.getNumber() == sourceAccount) {
                account.withdraw(value);
            }
            if (account.getNumber() == destinationAccount) {
                account.deposit(value);
            }
        }
    }

    public double checkBalance(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                return account.getBalance();
            }
        }

        return 1;
    }

    public int searchNumber(int accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getNumber() == accountNumber) {
                return i;
            } 

        }
        return -1;
    }

    public static void showAccounts() {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("== Account " + (i + 1) + " ==\n" + accounts.get(i).getNumber());

        }
    }
}