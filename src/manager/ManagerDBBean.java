package manager;

public class ManagerDBBean {
	private static ManagerDBBean instance = new ManagerDBBean();
	
	public ManagerDBBean getInstance() {
		return instance;
	}
	private ManagerDBBean() {}
	
	
}
