package be.motormouth.exceptions;

public class InvalidEmailException extends RuntimeException {
    public static final String EMAIL_VALIDATION_RULES = """
            For the local part : \n
            Numeric values allowed from 0 to 9.
            
            Both uppercase and lowercase letters from a to z are allowed.
            
            Allowed are underscore “_”, hyphen “-“, and dot “.”
            
            Dot isn’t allowed at the start and end of the local part.
            
            Consecutive dots aren’t allowed.
            
            For the local part, a maximum of 64 characters are allowed.
            
                                        
            For the domain part : \n
            Numeric values allowed from 0 to 9.
            
            We allow both uppercase and lowercase letters from a to z.
            
            Hyphen “-” aren’t allowed at the start and end of the domain part.
            
            Dot “.” aren’t allowed at the end of the domain part.
            
            No consecutive dots.
                               
            """;

    private static String buildMessage(String email){
        return "Email \"" + email + "\" is not valid. Follow those rules : \n" +
                EMAIL_VALIDATION_RULES;
    }

    public InvalidEmailException(String email) {
        super(buildMessage(email));
    }
}
