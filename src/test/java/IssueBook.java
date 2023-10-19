
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    ByteArrayOutputStream errorMessage;

    @BeforeEach
    public void setTests(){
       lc  = new LibraryCard(new Student("john", 0), new Date(2023, 10, 10), new Date (2024, 10, 10), 0);
       newBook = new Book(1, "Pride and prejudice", 0);
       todaysDate = new Date(2023, 10, 11);
       errorMessage = new ByteArrayOutputStream();
       System.setErr(new PrintStream(errorMessage));
        
    }


    //Baseline test. Should always succeed.
    @Test
    public void isseBook_True_AllVariablesAreInOrder(){
        assertTrue(lc.issueBook(newBook, todaysDate));
    }



    //Tests if the same book can be issued twice. Should throw error
    @Test
    public void issueBook_Equals_BookAlreadyOnTheAccount(){
        lc.issueBook(newBook, todaysDate);
        //newBook.setDemand(true);
        lc.issueBook(newBook, todaysDate);                        //Sets the book as available, as the b
        assertEquals("Book already loaned out", errorMessage.toString());
    }
	
    //Adds 4 dummy books. Tests if book can be borrowed with the multiple books. Should return false
    @Test
    public void issueBook_False_ManyBooksBorrowed(){
        lc.issueBook(new Book(2, "New Book", 0), todaysDate);
        lc.issueBook(new Book(3, "New Book", 0), todaysDate);
        lc.issueBook(new Book(4, "New Book", 0), todaysDate);
        lc.issueBook(new Book(5, "New Book", 0), todaysDate);


        assertFalse(lc.issueBook(newBook, todaysDate));
    }

    
    //Sets the date to be 1 year after expiry. Tests whether the book is issued with expired card. Should return false
    @Test
    public void issueBook_False_CardExpired(){
        todaysDate = new Date(2025, 10, 10);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }


    //Sets book as not available. Tests if the unavailable book can be issued. Should return false
    @Test 
    public void IssueBook_False_BookNotAvailable(){
        newBook.setDemand(false);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }

    //Issues a fine. Tests if the book is issued with outstanding fine. Should return false
    @Test
    public void IssueBook_False_StudentHasFine(){
        lc.setFine(10);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }


    //Tests if the book is updated after being issued. Should return false
    @Test
    public void Book_False_SuccessfullyBorrowed(){
        lc.issueBook(newBook, todaysDate);
        assertFalse(newBook.getStatus());
    }


    //Tests if book in low demand is issued for 15 days. Should be equal to 15
    @Test
    public void IssueBook_Equals_LowDemand(){
        lc.issueBook(newBook, todaysDate);
        assertEquals(newBook.days(), 15);
    }


    //Tests if the book in High Demand is issued for 3 days. Should be equal to 3
    @Test
    public void IssueBooke_Equals_HighDemand(){
        newBook.setDemand(1);
        lc.issueBook(newBook, todaysDate);
        assertEquals(newBook.days(), 3);
    }






    

    
	



}
