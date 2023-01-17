package Config;

import org.aeonbits.owner.Config;


//@Sources({
//    "classpath:qa.properties" // mention the property file name
//})

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:${user.dir}/src/main/java/Config/config.properties"})


public interface ReadPropertyFile extends Config {

	String environment();

	@Key("${environment}.url")
	String url();
	
	@Key("${environment}.api_url")
    String api_url();

	@Key("${environment}.user_id")
    int user_id();
    
	String ie_username();
    String ie_password();

    String version();
    
    String runmode();
    
    
//    @Key("db.hostname")
//    String getDBHostname();
//
//    @Key("db.port")
//    int getDBPort();
//
//    @Key("db.username")
//    String getDBUsername();
//
//    @Key("db.password")
//    String getDBPassword();
    
    

}