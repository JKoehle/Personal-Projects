package simpleBank;

public class BankAccount {
	public static void main(String[] args) {
	    
	  }
	String accountHolderName;
    String accountNumber;
    double accountBalance;
 
    void deposit(double amount) 
    {
        accountBalance += amount;
        System.out.printf("Receipt { $%.2f deposited. New balance is $%.2f }\n", amount, accountBalance);
    }
    
    void withdraw (double amount) 
    {
       accountBalance -= amount;
       System.out.printf("Receipt { $%.2f withdrawn. New balance is $%.2f }\n", amount, accountBalance);
    }
	   public static BankAccount makeBankAccount (String accname, String accnumber) {
	      BankAccount account = new BankAccount();
	      account.accountHolderName = accname;
	      account.accountNumber = accnumber; 
	      account.accountBalance =0.0;
	      return account; }
	   public static void displayAccountInfo (BankAccount account) {
	      System.out.println("Account number      : " + account.accountNumber);
	      System.out.println("Account holder name : " + account.accountHolderName);
	      System.out.printf("Account Balance     : $%.2f\n", account.accountBalance); }
	   public static void chargeServiceFee (BankAccount account, double fee) {
	      account.accountBalance -= fee; }
	   public static void applyInterest (BankAccount account, double rate) {
	      double interest = account.accountBalance * rate;
	      account.accountBalance += interest; }
	   public static void payBill (BankAccount account, double bill) {
	      account.withdraw(bill);
	      }
	   public static void depositChecks (BankAccount account, double pay) {
	      account.deposit(pay);
	      }
}
