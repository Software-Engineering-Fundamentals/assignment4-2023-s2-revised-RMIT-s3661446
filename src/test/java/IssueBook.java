
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



    //The book is issue twice. Should throw error
    @Test
    public void issueBook_Equals_BookAlreadyOnTheAccount(){
        lc.issueBook(newBook, todaysDate);
        //newBook.setDemand(true);
        lc.issueBook(newBook, todaysDate);                        //Sets the book as available, as the b
        assertEquals("Book already loaned out", errorMessage.toString());
    }
	
    //Adds 4 dummy books. Should return false
    @Test
    public void issueBook_False_ManyBooksBorrowed(){
        lc.issueBook(new Book(2, "New Book", 0), todaysDate);
        lc.issueBook(new Book(3, "New Book", 0), todaysDate);
        lc.issueBook(new Book(4, "New Book", 0), todaysDate);
        lc.issueBook(new Book(5, "New Book", 0), todaysDate);


        assertFalse(lc.issueBook(newBook, todaysDate));
    }

    
//Sets the date to be 1 year after expiry/ Should return false
    @Test
    public void issueBook_False_CardExpired(){
        todaysDate = new Date(2025, 10, 10);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }


    //Sets book as not available. Should return false
    @Test 
    public void IssueBook_False_BookNotAvailable(){
        newBook.setDemand(false);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }

    //Issues a fine. Should return false
    @Test
    public void IssueBook_False_StudentHasFine(){
        lc.setFine(10);
        assertFalse(lc.issueBook(newBook, todaysDate));
    }


    //Tests that the book is updated
    @Test
    public void Book_False_SuccessfullyBorrowed(){
        lc.issueBook(newBook, todaysDate);
        assertFalse(newBook.getStatus());
    }


    //Book in low deman is issued for 15 days.
    @Test
    public void IssueBook_Equals_LowDemand(){
        lc.issueBook(newBook, todaysDate);
        assertEquals(newBook.days(), 15);
    }


    //Book in High Demand is issued for 3 days
    @Test
    public void IssueBooke_Equals_HighDemand(){
        newBook.setDemand(1);
        lc.issueBook(newBook, todaysDate);
        assertEquals(newBook.days(), 3);
    }






    

    
	



}
