package com.ceiba;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(ConfiguracionBase.class)
@ComponentScan("com.ceiba")
public class ApplicationMock {
   
	
}
