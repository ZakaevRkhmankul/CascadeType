package peaksoft.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.entities.Car;
import peaksoft.entities.User;

import java.util.Properties;

public class HibernateConfig {
    public static EntityManagerFactory entityManagerFactory(){
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_DRIVER,"org.postgresql.Driver");
        properties.put(Environment.JAKARTA_JDBC_URL,"jdbc:postgresql://localhost:5432/peaksoft");
        properties.put(Environment.JAKARTA_JDBC_USER,"postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD,"1234");
        properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.HBM2DDL_AUTO,"update");
        properties.put(Environment.SHOW_SQL,true);
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.setProperties(properties);
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);

    }
}
