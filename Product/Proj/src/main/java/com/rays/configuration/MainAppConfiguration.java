package com.rays.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.rays.dao.EmployeeDAO;
import com.rays.dao.EmployeeDAOImpl;
import com.rays.service.EmployeeService;
import com.rays.service.EmployeeServiceImpl;
import com.rays.util.CassandraUtil;
import com.rays.util.MyCassandraTemplate;

@Configuration
@Import(CassandraUtil.class)

public class MainAppConfiguration {
	
	
    
	/**
     * Creating the EmployeeService bean.
     * @return {@link EmployeeService}
     */
    @Bean
    public EmployeeService getEmployeeService() {
        return new EmployeeServiceImpl();
    }

    /**
     * Creating the EmployeeDAO bean.
     * @return {@link EmployeeDAO}
     */
    @Bean
    public EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAOImpl();
    }
    
    /**
     * Creating the MyCassandraTemplate bean.
     * @return {@link MyCassandraTemplate}
     */
    @Bean
    public MyCassandraTemplate getMyCassandraTemplate() {
        return new MyCassandraTemplate();
    }
}