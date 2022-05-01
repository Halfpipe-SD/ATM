public interface ATMActionsListener {

  public enum USER {
    WITHDRAWAL, DEPOSIT, BALANCE_INQUIRY, EXIT
  };

  public void atmUserAction(USER action);

  public void atmEnterAction(String input);

}
