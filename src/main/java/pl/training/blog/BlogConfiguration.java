package pl.training.blog;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class BlogConfiguration {

    @Bean
    public DataSource dataSource(Environment environment) {
        var datasource = new HikariDataSource();
        datasource.setUsername(environment.getProperty("database.username"));
        datasource.setPassword(environment.getProperty("database.password"));
        datasource.setJdbcUrl(environment.getProperty("database.url"));
        datasource.setDriverClassName(environment.getProperty("database.driver"));
        datasource.setMaximumPoolSize(Integer.parseInt(environment.getProperty("database.pool-size")));
        return datasource;
    }

    @Bean
    public PropertiesFactoryBean jpaProperties() {
        var factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("jpa.properties"));
        return factoryBean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, @Qualifier("jpaProperties") Properties jpaProperties) {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("pl.training.blog");
        factoryBean.setJpaProperties(jpaProperties);
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
