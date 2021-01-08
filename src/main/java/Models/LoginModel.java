package Models;

public class LoginModel {

	public String email;
	public String password;
	public String loginScenario;
	public String passwordScenario;
	public String message;
	public String description;
	public String url;
	public String passScenario;
	public String failScenario;
	public boolean invalidCredentials;
	public boolean emptyCredentials;
	public boolean validCredentials;
	
	public LoginModel (String _email, String _password, String _loginScenario, String _passwordScenario, String _message, String _description, 
			String _url, String _passScenario, String _failScenario, boolean _invalidCredentials, boolean _validCredentials)
	
	{
		this.email = _email;
		this.password = _password;
		this.loginScenario = _loginScenario;
		this.passwordScenario = _passwordScenario;
		this.message = _message;
		this.description = _description;
		this.url = _url; 
		this.passScenario = _passScenario;
		this.failScenario = _failScenario;
		this.invalidCredentials = _invalidCredentials;
		this.validCredentials = _validCredentials;
	}
	
	
}
