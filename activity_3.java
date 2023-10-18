package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Department in an organisation
 */
public class Department {
    
    private String name;


    //Problem: Naming conventions make it hard to identify elements. For example, instead naming the arraylist of IDs "ID1", it's better to have it named IDArray or similar to better indicate its purpose
    //A lot of the functions use "this.". This step invokes the constructor, which can make functions work incorrectly
    
    private ArrayList<String> IDArray = new ArrayList<String>();
    
    //private ArrayList<String> temp1 = new ArrayList<String>();


    //Correction. Primitives do no go into an Array List
    /*
     * ORIGINAL:
     * private ArrayList<Integer> employee = new ArrayList<Integer>();
     */


    
    private ArrayList<Integer> employee = new ArrayList<Integer>();

    
    private int ID2;

    //Correction: name is duplicated
    //private String name;

    public String getID() {
        return ID2;
    }

    public void setID(int ID) {
        this.ID2 = ID;
    }

    
    public boolean checkEmployee(int eID) { //refactored

        //ORIGINAL: int p=0;
        //The function used int as a variable that could have been either 0 or 1. Boolean is much more fitting for it
        //As a result, new boolean variable was created in its stead which can subsequently be returned
        //The boolean is set to false by default

	    
        boolean employeeFound = false;
	    for (int i = 0; i < employee.size(); i++){

            //ORIGINAL: check=this.employee.get(i);
            //It is not necessary to cache the current element as it's only used for comparison


            if (employee.get(i) == eID) {
                //ORIGINAL: p=1;  As mentioned earlier, "p" variable is now redundant, and instead, if the If statement is true, it will set the boolean to true
                employeeFound = true;
            }
        }


        //Since a new boolean was added, there was and int p removed, there is no point at all in making the return depend on the if statement
        //Instead it just checks if the earlier declared boolean is true to print

    

        if (employeeFound){
            System.out.println("Employee exists");
            //return true
        }
        else{
            System.out.println("Employee doesn't exists");
            //return false
	    }


        //The function returns the earlier boolean variable.
        return employeeFound;
    }

    
    //temporary array list was being cached within the class, as opposed to being cached within the function
    public void assignNew(string temp2, int ID) {
        ArrayList<String> temp1 = new ArrayList<String>();
        temp1.add(temp2);
	    IDArray.add(ID);
    }




}
