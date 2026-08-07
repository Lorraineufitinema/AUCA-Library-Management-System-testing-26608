package com.auca.domain;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "membership_type")
public class MembershipType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID membershipTypeId;

    @Column(nullable = false, unique = true)
    private String membershipName;

    @Column(nullable = false)
    private int dailyFee;

    @Column(nullable = false)
    private int maxBooks;

    @Column(nullable = false)
    private int loanPeriodDays;

    public MembershipType() {
    }

    public MembershipType(UUID membershipTypeId, String membershipName,
            int dailyFee, int maxBooks, int loanPeriodDays) {
        this.membershipTypeId = membershipTypeId;
        this.membershipName = membershipName;
        this.dailyFee = dailyFee;
        this.maxBooks = maxBooks;
        this.loanPeriodDays = loanPeriodDays;
    }

    public UUID getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(UUID membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public int getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(int dailyFee) {
        this.dailyFee = dailyFee;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public int getLoanPeriodDays() {
        return loanPeriodDays;
    }

    public void setLoanPeriodDays(int loanPeriodDays) {
        this.loanPeriodDays = loanPeriodDays;
    }
}