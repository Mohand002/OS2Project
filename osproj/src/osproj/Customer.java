package osproj;


import java.util.Date;
import osproj.Bshop;

class Customer implements Runnable {

    int customerId;
    Date inTime;
 
    Bshop shop;
 
    public Customer(Bshop shop) {
    
        this.shop = shop;
    }
 
    public int getCustomerId() {										//getter and setter methods
        return customerId;
    }
 
    public Date getInTime() {
        return inTime;
    }
 
    public void setcustomerId(int customerId) {
        this.customerId = customerId;
    }
 
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
 
    public void run() {													//customer thread goes to the shop for the haircut  
//run this code in a Different thraeds  
    
        goForHairCut();
    }
    private synchronized void goForHairCut() {					 // locks the resources to a thread so that no other thread can access it at a time
    
        shop.add(this);                                                    		//customer is added to the list   
    }
}