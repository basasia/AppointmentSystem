/*
Class: AppointmentCalendar
Description: Object representation of details of appointment that show on CalendarView
Created: 12/03/2020
Updated: 21/03/2020
Authors: Asia Benyadilok
*/
package hospmansys.PatientMakeAppointmentClass;

import java.util.ArrayList;

public class ExternalPatient {
    //create all attributes of patient
    private String patientID;
    private String firstname;
    private String surname;
    private String dateOfBirth;
    private String email;
    private String bookingStatus;
    private String prefDate;
    private String prefTime;
    //create array list to contain patient objects
    private static ArrayList<ExternalPatient> externalPatientList = new ArrayList<ExternalPatient>();
    
     //constructor: AppointmentCalendar
     //description: initialise all variables when creating a new instanc
     //parameters: none
    public ExternalPatient(String patientID, String firstname, String surname, String dateOfBirth, String email, String bookingStatus)
    {
    this.patientID = patientID; 
    this.firstname = firstname;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;  
    this.email = email; 
    this.bookingStatus = bookingStatus; 
    //generate preference date and time as empty String
    this.prefDate = "";
    this.prefTime = "";
    }
   
    //get and set methods for each arttibute
    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    
    public String getPrefDate() {
        return prefDate;
    }
    
    public String getPrefTime() {
        return prefTime;
    }
    
    public void setAppointment(String prefDate , String prefTime ) {
        this.prefDate = prefDate;
        this.prefTime = prefTime;
    }
    
        
    //method: getExternalPatientList
    //parameters: none
    //Description: method to get patient list
    public static ArrayList<ExternalPatient>  getExternalPatientList() {
        return ExternalPatient.externalPatientList;
    }
    
    //method: setExternalPatientList
    //parameters: none
    //Description: method to add patient to patient list
    public static void  setExternalPatientList(ExternalPatient patient) {
        ExternalPatient.externalPatientList.add(patient);
    }
    
    
    
}
