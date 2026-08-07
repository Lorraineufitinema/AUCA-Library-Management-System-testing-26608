package com.auca.service;

import java.util.UUID;

import com.auca.dao.MembershipDao;
import com.auca.dao.MembershipTypeDao;
import com.auca.dao.UserDao;
import com.auca.domain.Membership;
import com.auca.domain.MembershipType;
import com.auca.domain.User;
import com.auca.enums.Status;
import com.auca.util.HibernateUtil;

public class MembershipService {

    private MembershipDao membershipDao = new MembershipDao(HibernateUtil.buildSessionFactory("application.properties"));
    private MembershipTypeDao membershipTypeDao = new MembershipTypeDao(HibernateUtil.buildSessionFactory("application.properties"));
    private UserDao userDao = new UserDao(HibernateUtil.buildSessionFactory("application.properties"));

    public Membership registerMembership(UUID userId, UUID membershipTypeId) {
        long activeCount = membershipDao.countActiveMembershipsByUserId(userId);
        if (activeCount > 0) {
            throw new RuntimeException("User already has active membership");
        }

        User user = userDao.findById(userId);
        MembershipType membershipType = membershipTypeDao.findById(membershipTypeId);

        Membership membership = new Membership();
        membership.setReader(user);
        membership.setMembershipType(membershipType);
        membership.setStatus(Status.PENDING);

        membershipDao.save(membership);
        return membership;
    }

}
