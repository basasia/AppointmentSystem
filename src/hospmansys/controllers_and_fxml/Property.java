/*
Class: Property
Description: stores all the data in temporary memory to be displayed on the gui.
Created: 01/02/2020
Updated: 24/02/2020
Authors: Asia Benyadilok.
*/
package hospmansys.controllers_and_fxml;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Property {
         
    private SimpleStringProperty time;
    private SimpleStringProperty doctorName;
    private ArrayList<LocalDate> unavailableDate = new ArrayList<LocalDate>();


     //Constructor: Property
    //Description: is populated with data from UWEAccommodationSystem
    //Parameters: the details to be displayed on the gui table.
    public Property(String doctorName,String time){
        
     this.doctorName = new SimpleStringProperty(doctorName);

     this.time = new SimpleStringProperty(time);

    }

    //getters and setters
    public void setTime(String time) {
        this.time = new SimpleStringProperty(time);
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = new SimpleStringProperty(doctorName);
    } 

    public String getTime() {
        return time.get();
    }

    public String getDoctorName() {
        return doctorName.get();
    }
    
    public void setUnavailableDate(LocalDate date){
       this.unavailableDate.add(date);
    }
    
    public boolean checkAvailability(LocalDate date){
        for (int i = 0;i< this.unavailableDate.size();i++){
            if (date.equals(this.unavailableDate.get(i)))
            {
                return false;
            
            }
    }
        return true;
    }
}
