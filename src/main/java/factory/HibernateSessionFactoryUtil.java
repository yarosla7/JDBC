package factory;

import models.Stuff;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) { //Проверка инициализирована ли фабрика сессий
            try {
                Configuration configuration = new Configuration().configure(); // Если нет, то мы загружаем настройки Hibernate из файла конфигурации
                configuration.addAnnotatedClass(Stuff.class); // добавляем класс Stuff в список классов, обрабатываемых Hibernate
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());  // создаем объект StandardServiceRegistryBuilder и применяем настройки из файла конфигурации
                sessionFactory = configuration.buildSessionFactory(builder.build());  // тут создаем фабрику сессий на основе конфигурации и сервисного реестра
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}