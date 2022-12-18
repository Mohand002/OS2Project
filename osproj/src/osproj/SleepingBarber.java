package osproj;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SleepingBarber {
	
	public static void main (String a[]) throws InterruptedException {	
		
		int noOfBarbers , customerId=1, noOfCustomers, noOfChairs;	//initializing the number of barber and customers
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of barbers(M):");			//input M barbers
    	noOfBarbers=sc.nextInt();
    	
    	System.out.println("Enter the number of waiting room"			//input N waiting chairs
    			+ " chairs(N):");
    	noOfChairs=sc.nextInt();
    	
    	System.out.println("Enter the number of customers:");			//inout the number of customers for the shop
    	noOfCustomers=sc.nextInt();
    	
		ExecutorService exec = Executors.newFixedThreadPool(20);		//initializing with 12 threads as multiple of cores in the CPU, here 6
    	Bshop shop = new Bshop(noOfBarbers, noOfChairs);				//initializing the barber shop with the number of barbers
    	Random r = new Random();  										//a random number to calculate delays for customer arrivals and haircut
       	    	
        System.out.println("\nBarber shop opened with "
        		+noOfBarbers+" barber(s)\n");
        
        long startTime  = System.currentTimeMillis();					//start time of program
        
        for(int i=1; i<=noOfBarbers;i++) {								//generating the specified number of threads for barber
        	
        	Barber barber = new Barber(shop, i);	
        	Thread thbarber = new Thread(barber);
            exec.execute(thbarber);
        }
        
        for(int i=0;i<noOfCustomers;i++) {								//customer generator; generating customer threads
        
            Customer customer = new Customer(shop);
            customer.setInTime(new Date());
            Thread thcustomer = new Thread(customer);
            customer.setcustomerId(customerId++);
            exec.execute(thcustomer);
            
            try {
            	
            	double val = r.nextGaussian() * 2000 + 2000;			//'r':object of Random class, nextGaussian() generates a number with mean 2000 and	
            	int millisDelay = Math.abs((int) Math.round(val));		//standard deviation as 2000, thus customers arrive at mean of 2000 milliseconds
            	Thread.sleep(millisDelay);								//and standard deviation of 2000 milliseconds
            }
            catch(InterruptedException iex) {
            
                iex.printStackTrace();
            }
            
        }
        
        exec.shutdown();												//shuts down the executor service and frees all the resources
        exec.awaitTermination(12, SECONDS);								//waits for 12 seconds until all the threads finish their execution 
 
        long elapsedTime = System.currentTimeMillis() - startTime;		//to calculate the end time of program from start point
        
        System.out.println("\nBarber shop closed");
        System.out.println("\nTotal time elapsed in seconds"
        		+ " for serving "+noOfCustomers+" customers by "
        		+noOfBarbers+" barbers with "+noOfChairs+
        		" chairs in the waiting room is: "
        		+TimeUnit.MILLISECONDS
        	    .toSeconds(elapsedTime));
        
        System.out.println("\nTotal customers: "+noOfCustomers+
        		"\nTotal customers served: "+shop.getTotalHairCuts()
        		+"\nTotal customers lost: "+shop.getCustomerLost());
               
        sc.close();
    }
}
 

