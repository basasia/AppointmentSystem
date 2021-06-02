/*
Class: AppointmentCalendar
Description: Object representation of details of appointment that show on CalendarView
Created: 12/03/2020
Updated: 21/03/2020
Authors: Asia Benyadilok
*/
package hospmansys.PatientMakeAppointmentClass;

import hospmansys.controllers_and_fxml.Property;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class AppointmentCalendar {
    //construct instance of AppointmentCalendar
     private static AppointmentCalendar appointmentCalendar = null;
     //construct arraylist of available date and time
     private static ArrayList<Integer> availableDate = new ArrayList<Integer>();
     private static ArrayList<Property> availableTimeAndDoctor = new ArrayList<Property>();
     //construct random
     private Random rand = new Random();
     
     //constructor: AppointmentCalendar
     //description: initialise all variables when creating a new instanc
     //parameters: none
     private AppointmentCalendar() 
    { 
        //set available date
        for (int i=1;i<32;i+=rand.nextInt(3))
        {
            availableDate.add(i);
        }
        
        int numName = 1;
        //set available time and doctor name
        for (int i = 10;i<19;i++)
        {
            availableTimeAndDoctor.add(new Property("Doc"+Integer.toString(numName),Integer.toString(i)+":00 "));
            numName++;
        }
        
    }
     
    //method: getInstance
    //parameters: none
    //Description: method to get instance of AppointmentCalendar (singleton)
    public static AppointmentCalendar getInstance() 
    { 
        if (appointmentCalendar == null) 
            appointmentCalendar = new AppointmentCalendar(); 
  
        return appointmentCalendar; 
    } 
    
    //method: getAvailableTimeAndDoctor
    //parameters: none
    //Description: method to get array list of availableTimeAndDoctor
    public static ArrayList<Property> getAvailableTimeAndDoctor()
    {
         return availableTimeAndDoctor;
    } 
    
    //method: getAvailableDate
    //parameters: none
    //Description: method to get array list of availableDate
    public static ArrayList<Integer> getAvailableDate()
    {
         return availableDate;
    } 
    
  
}
