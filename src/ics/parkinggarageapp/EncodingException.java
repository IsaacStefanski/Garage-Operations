package ics.parkinggarageapp;

/**
 * EncodingException, thrown when there is an error in encoding data to save to
 * a file
 * 
 * @author Isaac
 */
public class EncodingException extends Throwable {
    private static String message = "Sorry, data could not be encoded for saving.";
    public EncodingException(){
        super(message);
    }
}