package com.auca.domain;

import java.util.UUID;

import com.auca.enums.Status;

import jakarta.persistence.*;

@Entity
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID membershipId;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private User reader;

    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private MembershipType membershipType;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Membership() {
    }

    public Membership(UUID membershipId, User reader,
            MembershipType membershipType, Status status) {
        this.membershipId = membershipId;
        this.reader = reader;
        this.membershipType = membershipType;
        this.status = status;
    }

    public UUID getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(UUID membershipId) {
        this.membershipId = membershipId;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}