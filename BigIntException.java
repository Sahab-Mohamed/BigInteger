/*Sahab Moahmed
 *Professor Shannon
 *CSC 223
 *
 *The exception class for handling runtime exception
 *09/17/2023 
 */
package bigIntPackage;
/*
 * Extending the child class Invalid Data form the superclass Exception
 */


@SuppressWarnings("serial")
public class BigIntException extends RuntimeException {
	
	
	public BigIntException(String message) {
		
		super(message);
	}
	
}
