package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Department in an organisation
 */
public class Department {
    
    private String name;
    
    private ArrayList<String> ID1 = new ArrayList<String>();
    
    private ArrayList<String> temp1 = new ArrayList<String>();


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

    
    public boolean checkEmployee(int eID) {

	    int p=0;
	    for (int i = 0; i < this.employee.size(); i++){
        check=this.employee.get(i);
            if (check==eID) {
                p=1;
            }
        }

        if (p==1){
            System.out.println("Employee exists");
            return true;
        }
        else{
            System.out.println("Employee doesn't exists");
            return false;
	    }
    }

    

    public void assignNew(string temp2, int ID) {
        this.temp1.add(temp2);
	  this.ID1.add(ID);
    }


}
