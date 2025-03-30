package mbs.main;
import com.mbs.adminlogin.Adminlogin;
import com.mbs.userlogin.Userlogin;
import com.mbs.userregister.*;
import java.util.Scanner;
public class Mbsmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		String get = "eql";
		String loopterminate= "eql";

		while(get.equals(loopterminate)) {
			System.out.println("......................................");
			System.out.println("Welcome to Movie Booking System");
			System.out.println("......................................");
			System.out.println("1.Adminlogin");
		    System.out.println("2.Userlogin");
		    System.out.println("3.Useregister");
		    System.out.println("4.Exit");
		    System.out.println("Enter the action you want to perform:");
		    
		Scanner din = new Scanner(System.in);
		 
		try {
			int choice = din.nextInt();
			switch(choice) {
			case 1 :Adminlogin.checkadmin();
			break;
			case 2 :Userlogin.userlogin(); 
			break;
			case 3:
				Userregister.createuser();
			break;	
			case 4:
				loopterminate = "noteql";
			System.out.println("Exited the loop");
			break;
			default:
				System.out.println("please Enter  number between 1-4");
			}
			
		}
		catch(Exception e) {
			System.out.println("invalid choice Please Enter Number");
			
		}
	}

}
}