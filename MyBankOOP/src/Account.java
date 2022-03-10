public class Account {
    private final String number, holder;
    private double balance;

    public Account(String number, String holder, double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }

    public String getNumber() {
        return this.number;
    }

    public String getHolder() {
        return this.holder;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void deposit(Account destination, double amount) {
        destination.setBalance(destination.getBalance() + amount);
    }

    public static boolean authenticate(Account source, double amount) {
        return source.getBalance() >= amount;
    }

    public static boolean withdraw(Account source, double amount) {
        if (authenticate(source, amount)) {
            source.setBalance(source.getBalance() - amount);

            return true;
        }
        return false;
    }

    public static boolean transfer(Account source, Account destination, double amount) {
        if (authenticate(source, amount)) {
            withdraw(source, amount);
            deposit(destination, amount);

            return true;
        }

        return false;

    }

    @Override
    public String toString() {
        return String.format("""
                Holder: %s;
                Number: %s;
                Balance: %.2f""", this.getHolder(), this.getNumber(), this.balance);
    }

}
