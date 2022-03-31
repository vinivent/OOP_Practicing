package Logic;
public class Account {
    private int accountNumber;
    private double balance;

    public Account(int number){
        this.accountNumber = number;
        this.balance = 0;
    }

    public int getNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double value){
        balance += value;
    }
    public boolean withdraw(double value){
        if(balance>=value){
            balance -= value;
            return true;
        }else{
            return false;
        }
    }
    
}