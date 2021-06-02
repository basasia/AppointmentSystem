/*
Class: LoginForPatient
Description: main use for validate surname and date of birth when user login.
             Also return object of patient that logged in to perform futher functions
Created: 12/03/2020
Updated: 21/03/2020
Authors: Asia Benyadilok
*/
package hospmansys.PatientMakeAppointmentClass;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;


public class LoginForPatient {
    
//generate external patient object    
private static ExternalPatient user;

//method: login
//parameters: surname - surname of patient, dateOfBirth - birth date of patient
//Description: method to check if patient surname and birth date are valid or not
public static boolean login(String surname,String dateOfBirth)
{
//loop through patients list to find the surname and date of birth that match with inputs (no matter if it is upper or lower case)
    for (int i=0;i<ExternalPatient.getExternalPatientList().size();i++){
        if (surname.equals(ExternalPatient.getExternalPatientList().get(i).getSurname())||
            surname.equals(toLowerCase(ExternalPatient.getExternalPatientList().get(i).getSurname()))||
            surname.equals(toUpperCase(ExternalPatient.getExternalPatientList().get(i).getSurname())))
        {
            
            if (dateOfBirth.equals(ExternalPatient.getExternalPatientList().get(i).getDateOfBirth()))
            {
                //save that patient object to user
                LoginForPatient.user = ExternalPatient.getExternalPatientList().get(i);
                return true;
            }
            
        }
    }
return false;
}

//method: getCurrentPatient 
//parameters: none 
//Description: method to get patient object that  loggedin to the program
public static ExternalPatient getCurrentPatient()
{
    return LoginForPatient.user;

}
}
