
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student 
 */
public class LibraryCard {
    /**
     * Card id 
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     *  Details about the cardholder
     */
    private Student student;




    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
	    this.ExpiryDate = ExpiryDate;
	    this.ID = ID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    
    public List<Book> getBooks() {
        return borrowed;
    }

    

    /**
     * Issue a new book
     * @param Book: book to borrow 
     * @return true if the book is successfully borrowed, false otherwise
     */

    public boolean issueBook(Book book, Date todayDate){
        boolean isTaken;

        try{
            if(borrowed.contains(book)){                        //Checks that book is already borrowed and throws exception if book is already taken. See catch for exception handling
                throw new IllegalBookIssueException ("Book already loaned out");
            }

            //State checks
            else if(borrowed.size() >= 4 ||                     //Checks that there is no more than 4 books
                    todayDate.compareTo(ExpiryDate) >= 0 ||     //Checks that the card is not expired
                    !book.getStatus() ||                        //Checks that the book is available
                    fine > 0                                    //Checks that there is no outstanding fines

            
            ){
                isTaken = false;                                //If checks fail, book is not borrowed
            }

            
            else{
                book.setDemand(false);                  //Marks book as unavailable (Will not be used in instances where there are multiple copies of the book)
                if (book.getDemand() > 0){                     
                    book.setDays(3);                      //If book is in high demand, loands out for 3 days
                }
                else{
                    book.setDays(15);                     //If book is not in a high deman, loans book out for 15 days
                }                  
                borrowed.add(book);                            //Adds book to the borowwed list
                isTaken = true;
            }
        }
        catch (IllegalBookIssueException e){                    //Handles Book issue exception
            System.err.println(e.getMessage());                 //Shows the error message
            isTaken = false;
        }

        return isTaken;

   
    }




}
