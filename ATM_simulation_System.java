import java.util.Scanner;

class Account {
    private double balance;
    private int pin;

    public Account(double initialBalance, int pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    public boolean authenticate(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class ATM {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (account.authenticate(enteredPin)) {
            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your current balance is: " + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Incorrect PIN. Access Denied.");
        }
        scanner.close();
    }
}

public class ATM_simulation_System {
    public static void main(String[] args) {
        Account userAccount = new Account(1000.0, 1234);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
