package org.d3.module.login;
import org.d3.module.BaseModule;
import org.d3.module.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class LoginModule extends BaseModule{
	
	private static Logger LOG = LoggerFactory.getLogger(LoginModule.class);

	public String getDescription() {
		return "Module for Login";
	}

	public int getType() {
		return Module.LOGIN;
	}

}
