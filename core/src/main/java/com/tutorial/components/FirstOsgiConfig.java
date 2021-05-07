package com.tutorial.components;

import org.osgi.service.metatype.annotations.*;
//import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "My First OSGI Configuration", description = "first configuration")
public @interface FirstOsgiConfig {

	@AttributeDefinition(name = "Length" , description = "Length of the Rectangle",type = AttributeType.INTEGER)
	int length();
	
	@AttributeDefinition(name = "Breadth" , description = "Breadth of the Rectangle",type = AttributeType.INTEGER)
	int breadth();
	
	@AttributeDefinition(name = "Password" , description = "Enter Password",type = AttributeType.PASSWORD)
	String password();
}
