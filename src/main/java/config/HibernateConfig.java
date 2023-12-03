package config;

import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    public static Configuration getConfiguration(){

        return new Configuration();

    }
}
