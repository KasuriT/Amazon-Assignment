package Models;

public class LoginModel {

	public String email;
	public String password;
	public String loginStep;
	public String passwordStep;
	public String testCaseName;
	public boolean invalidCredentials;
	
	public LoginModel (String _email, String _password, String _loginStep, String _passwordStep, String _testCaseName, boolean _invalidCredentials)
	
	{
		this.email = _email;
		this.password = _password;
		this.loginStep = _loginStep;
		this.passwordStep = _passwordStep;
		this.testCaseName = _testCaseName;
		this.invalidCredentials = _invalidCredentials;
	}
}
