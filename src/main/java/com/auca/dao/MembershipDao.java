package com.auca.dao;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.auca.domain.Membership;
import com.auca.enums.Status;

public class MembershipDao {

    private final SessionFactory sessionFactory;

    public MembershipDao(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public String save(Membership membership){

        Transaction transaction=null;

        try(Session session=sessionFactory.openSession()){

            transaction=session.beginTransaction();

            session.persist(membership);

            transaction.commit();

            return "Membership saved";

        }catch(RuntimeException ex){

            if(transaction!=null){
                transaction.rollback();
            }

            return ex.getMessage();
        }

    }

    public Membership findById(UUID id){

        try(Session session=sessionFactory.openSession()){

            return session.get(Membership.class,id);

        }

    }

    public Membership findApprovedByUserId(UUID userId){

        try(Session session=sessionFactory.openSession()){

            return session.createQuery(
                    "from Membership where reader.personId=:userId and status=:status",
                    Membership.class)
                    .setParameter("userId", userId)
                    .setParameter("status", Status.APPROVED)
                    .uniqueResult();

        }

    }

    public long countActiveMembershipsByUserId(UUID userId){

        try(Session session=sessionFactory.openSession()){

            return session.createQuery(
                    "select count(m) from Membership m where m.reader.personId=:userId and m.status=:status",
                    Long.class)
                    .setParameter("userId", userId)
                    .setParameter("status", Status.APPROVED)
                    .uniqueResult();

        }

    }

}