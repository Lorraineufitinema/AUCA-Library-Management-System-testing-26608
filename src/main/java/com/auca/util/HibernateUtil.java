package com.auca.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.auca.domain.Book;
import com.auca.domain.Borrower;
import com.auca.domain.Location;
import com.auca.domain.Membership;
import com.auca.domain.MembershipType;
import com.auca.domain.Room;
import com.auca.domain.Shelf;
import com.auca.domain.User;

public class HibernateUtil {

    public static SessionFactory buildSessionFactory(
            String propertiesFile) {

        try (InputStream input = HibernateUtil.class
                .getClassLoader()
                .getResourceAsStream(propertiesFile)) {

            Properties properties = new Properties();
            properties.load(input);

            return new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Location.class)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Borrower.class)
                    .addAnnotatedClass(Room.class)
                    .addAnnotatedClass(Shelf.class)
                    .addAnnotatedClass(Membership.class)
                    .addAnnotatedClass(MembershipType.class)
                    .buildSessionFactory();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}