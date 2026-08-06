package com.auca.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.auca.domain.MembershipType;

public class MembershipTypeDao {

    private final SessionFactory sessionFactory;

    public MembershipTypeDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public MembershipType findByName(String name){

        try(Session session=sessionFactory.openSession()){

            return session.createQuery(
                    "from MembershipType where membershipName=:name",
                    MembershipType.class)
                    .setParameter("name",name)
                    .uniqueResult();

        }

    }

}