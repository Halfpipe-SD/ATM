package klassen;

public class Account {

  private String username;
  private String accountNumber;
  private String pin;
  private double availableBalance;
  private double totalBalance;
  private boolean isAdmin;

  public Account(String username, String accountNumber, String pin, double availableBalance, double totalBalance,
      boolean isAdmin) {
    this.username = username;
    this.accountNumber = accountNumber;
    this.pin = pin;
    this.availableBalance = availableBalance;
    this.totalBalance = totalBalance;
    this.isAdmin = isAdmin;
  }

  private double roundToTwoDecimals(double d) {
    // TODO Implementierung einer Funktion, die auf zwei Nachkommastellen rundet
    return d;
  }

  public boolean validatePIN(String userPIN) {
    if (userPIN == pin)
      return true;
    else
      return false;
  }

  public double getAvailableBalance() {
    return roundToTwoDecimals(availableBalance);
  }

  public double getTotalBalance() {
    return roundToTwoDecimals(totalBalance);
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getPin() {
    return pin;
  }

  public String getUsername() {
    return username;
  }

  public boolean getAdmin() {
    return isAdmin;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void setAvailableBalance(double availableBalance) {
    this.availableBalance = availableBalance;
  }

  public void setTotalBalance(double totalBalance) {
    this.totalBalance = totalBalance;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }
}