
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;


/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {



    LibraryCard lc;
    Book newBook;
    Date todaysDate;

    @BeforeEach
    public void setTests(){
       lc  = new LibraryCard(new Student("john", 0), new Date(2023, 10, 10), new Date (2024, 10, 10), 0);
       newBook = new Book(1, "Pride and prejudice", 0);
       todaysDate = new Date(2023, 10, 11);
        
    }


    //Baseline test. Should always succeed.
    @Test
    public void successfullyLoaned(){
        assertTrue(lc.issueBook(newBook, todaysDate));
    }



    //The book is issue twice. Should return false
    @Test
    public void testAlreadyBorrowed(){
        lc.issueBook(newBook, todaysDate);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }
	
    //Adds 4 dummy books. Should return false
    @Test
    public void manyBooksBorrowed(){
        lc.issueBook(new Book(2, "New Book", 0), todaysDate);
        lc.issueBook(new Book(3, "New Book", 0), todaysDate);
        lc.issueBook(new Book(4, "New Book", 0), todaysDate);
        lc.issueBook(new Book(5, "New Book", 0), todaysDate);


        assertFalse(lc.issueBook(newBook, todaysDate));
    }

    
//Sets the date to be 1 year after expiry/ Should return false
    @Test
    public void testExpired(){
        todaysDate = new Date(2025, 10, 10);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }


    //Sets book as not available. Should return false
    @Test 
    public void testNotAvailable(){
        newBook.setDemand(false);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }

    //Issues a fine. Should return false
    @Test
    public void testFine(){
        lc.setFine(10);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }


    //Tests that the book is updated
    @Test
    public void testBookUpdate(){
        lc.issueBook(newBook, todaysDate);
        assertFalse(newBook.getStatus());
    }


    //Book in low deman is issued for 15 days.
    @Test
    public void testLowDemand(){
        lc.issueBook(newBook, todaysDate);
        assertEquals(newBook.days(), 15);
    }


    //Book in High Demand is issued for 3 days
    @Test
    public void testHighDeman(){
        newBook.setDemand(1);
        lc.issueBook(newBook, todaysDate);
        assertEquals(newBook.days(), 3);
    }






    

    
	



}
