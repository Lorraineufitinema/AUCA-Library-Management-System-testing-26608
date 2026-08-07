package com.auca.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.auca.dao.BorrowerDao;
import com.auca.dao.BookDao;
import com.auca.dao.MembershipDao;
import com.auca.domain.Book;
import com.auca.domain.Borrower;
import com.auca.domain.Membership;
import com.auca.domain.User;
import com.auca.enums.BookStatus;
import com.auca.exception.BorrowLimitExceededException;
import com.auca.util.HibernateUtil;

public class BorrowerService {

    private BorrowerDao borrowerDao = new BorrowerDao(HibernateUtil.buildSessionFactory("application.properties"));
    private BookDao bookDao = new BookDao(HibernateUtil.buildSessionFactory("application.properties"));
    private MembershipDao membershipDao = new MembershipDao(HibernateUtil.buildSessionFactory("application.properties"));

    public Borrower borrowBook(UUID readerId, UUID bookId) {
        validateBorrowLimit(readerId);

        Book book = bookDao.findById(bookId);
        if (book == null || book.getStatus() != BookStatus.AVAILABLE) {
            throw new RuntimeException("Book not available");
        }

        Membership membership = membershipDao.findApprovedByUserId(readerId);
        if (membership == null) {
            throw new RuntimeException("User does not have approved membership");
        }

        User reader = membership.getReader();

        Borrower borrower = new Borrower();
        borrower.setReader(reader);
        borrower.setBook(book);
        borrower.setPickupDate(LocalDate.now());
        borrower.setDueDate(LocalDate.now().plusDays(membership.getMembershipType().getLoanPeriodDays()));
        borrower.setFine(0);

        borrowerDao.save(borrower);

        book.setStatus(BookStatus.BORROWED);
        bookDao.update(book);

        return borrower;
    }

    public void validateBorrowLimit(UUID readerId) {
        Membership membership = membershipDao.findApprovedByUserId(readerId);
        if (membership == null) {
            throw new BorrowLimitExceededException("User does not have approved membership");
        }

        long activeBorrows = borrowerDao.countActiveBorrows(readerId);
        if (activeBorrows >= membership.getMembershipType().getMaxBooks()) {
            throw new BorrowLimitExceededException("Borrow limit exceeded");
        }
    }

    public int calculateLateFee(UUID borrowerId) {
        Borrower borrower = borrowerDao.findById(borrowerId);
        if (borrower == null) {
            return 0;
        }

        LocalDate referenceDate = borrower.getReturnDate() != null ? borrower.getReturnDate() : LocalDate.now();
        long daysLate = ChronoUnit.DAYS.between(borrower.getDueDate(), referenceDate);
        if (daysLate <= 0) {
            return 0;
        }

        Membership membership = membershipDao.findApprovedByUserId(borrower.getReader().getPersonId());
        if (membership == null) {
            return 0;
        }

        int dailyRate = membership.getMembershipType().getDailyFee();
        return (int) daysLate * dailyRate;
    }

}
