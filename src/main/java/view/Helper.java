package view;

import controller.FlightController;
import model.User;

import java.util.List;

public class Helper {
    public static boolean passwordFormatCheck(String password){
        if(password.length() < 6){
            return false;
        }
        boolean digit = false;
        boolean upperCase = false;
        boolean lowerCase = false;
        char[] letters = password.toCharArray();
        for(int i=0; i<letters.length; i++){
            if(Character.isDigit(letters[i])){
                digit = true;
            }
            if(Character.isUpperCase(letters[i])){
                upperCase = true;
            }
            if(Character.isLowerCase(letters[i])){
                lowerCase = true;
            }
        }
        return digit && upperCase && lowerCase;
    }
    
    public static boolean emailFormatCheck(String email){
        char[] letter = email.toCharArray();
        int aIndex = 0;
        int dotIndex = 0;
        for(int i=0; i<letter.length; i++){
            if(letter[i] == '.'){
                dotIndex = i;
            }
        }
        for(int i=letter.length - 1; i >= 0; i--){
            if(letter[i] == '@'){
                aIndex = i;
            }
        }
        System.out.println(aIndex + " " + dotIndex);
        return aIndex != 0 && dotIndex != 0 && dotIndex - aIndex > 1;
    }

    public static boolean flightExists(User user, String fromTo){
        List<String> flights = FlightController.getInstance().getFlightsDepartureArrival(user);
        for(String flight : flights){
            if(flight.equals(fromTo)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkTimeFormat(String time){
        if(time.length() > 5){
            return false;
        }
        String[] parts = time.split(":");
        if(parts.length != 2){
            return false;
        }
        if(parts[0].length() != 2 || parts[1].length() != 2){
            return false;
        }
        try{
            int hour = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            if(hour > 23 || minutes > 59){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean checkPrice(String price){
        try{
            int i = Integer.parseInt(price);
            if(i <= 0){
                return false;
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public static String addTimes(String time1, String time2){
        String[] parts1 = time1.split(":");
        String[] parts2 = time2.split(":");
        int hours1 = Integer.parseInt(parts1[0]);
        int hours2 = Integer.parseInt(parts2[0]);
        int minutes1 = Integer.parseInt(parts1[1]);
        int minutes2 = Integer.parseInt(parts2[1]);
        int hours = hours1 + hours2;
        int minutes = minutes1 + minutes2;

        if (minutes > 59){
            hours++;
            minutes -= 60;
        }

        if(hours > 24){
            hours -= 24;
        }

        String result = hours + ":" + minutes;
        return result;
    }
}
