package com.ceiba;

import org.springframework.context.annotation.Import;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@JdbcTest
@EnableTransactionManagement
@ComponentScan("com.ceiba.infraestructura.jdbc")
@Import(ConfiguracionBase.class)
public class ConfiguracionDaoTest {
    
}
