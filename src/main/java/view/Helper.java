package view;

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
}
