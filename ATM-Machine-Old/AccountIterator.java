import java.util.ArrayList;

public class AccountIterator implements Iterator {
	ArrayList<Account> accounts;

	public AccountIterator(ArrayList<Account> accounts2) {
		this.accounts = accounts2;
	}

	public boolean hasNext(int position) {
		if (position >= accounts.size()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Object next(int position) {
		Account AccountItem = accounts.get(position);
		return AccountItem;
	}

	@Override

	public boolean hasPrev(int position) {
		if (position == 0)
			return false;
		else
			return true;
	}

}
