package ru.broject.site.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by vyacheslav.svininyh on 21.01.2016.
 */
@Configuration
public class DataSourceConfiguration {

    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.user}")
    private String user;
    @Value("${dataSource.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.addDataSourceProperty("url", url);
        dataSource.addDataSourceProperty("user", user);
        dataSource.addDataSourceProperty("password", password);
        return dataSource;
    }
}
