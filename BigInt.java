/*Sahab Moahmed
 *Professor Shannon
 *CSC 223
 *
 *BigInt class with all the required methods and fields
 *09/17/2023 
 */
package bigIntPackage;
import java.util.Arrays;
//import exceptionPackage.BigIntException;

public class BigInt {
	
	private byte[] BIG_NUMBER;
	private byte[] RESULT_BIG_NUMBER;
	private boolean METHODS_POSITVE_SIGN;
	final boolean POSITVE_SIGN = METHODS_POSITVE_SIGN;
	private String strRep;
	
	
	public BigInt() throws BigIntException {
		
	}
	
	
	public BigInt(String strNum) throws BigIntException {
		BIG_NUMBER = new byte[strNum.length()];
		// check if the BigInt is properly received
		  
		  
		  boolean allZeros = true;
		  for(int i=0; i< strNum.length(); i++) {
			  if (strNum.charAt(i) == '0') {
		        continue;
		    }
			  else {
				  allZeros = false;
			  }
		  }
		  
		  if(allZeros) {
			    RESULT_BIG_NUMBER = new byte[1];
		        RESULT_BIG_NUMBER[0] = 0;
		        METHODS_POSITVE_SIGN = true;
		        return; // Exit the constructor early
		  }
		  
		  
		  
		//check the rest of the BigInt after the first index
		for (int i =1; i< strNum.length(); i++) {
			if (Character.isDigit(strNum.charAt(i))) {
				continue;
				
			}
			else {
				throw new BigIntException("Enter a valid number!");
			}
			
		}
		
		
			//check the first index
			if (strNum.charAt(0) == '-') { 
				METHODS_POSITVE_SIGN = false;
				BIG_NUMBER[0] = '-';// (byte) = 45
				
				for (int i =1; i< strNum.length(); i++) {
					//fill the array of Bytes (BigInt)
					String charAsString = String.valueOf(strNum.charAt(i));
					
					BIG_NUMBER[i] = Byte.parseByte(charAsString);
					
				}
			}
			else if (strNum.charAt(0) == '+') { 
				METHODS_POSITVE_SIGN = true;
				BIG_NUMBER[0] = '+'; // (byte) = 43
				for (int i =1; i< strNum.length(); i++) {
					//fill the array of Bytes (BigInt)
					String charAsString = String.valueOf(strNum.charAt(i));
					
					BIG_NUMBER[i] = Byte.parseByte(charAsString);
					
				}
			}
			
			else if (Character.isDigit(strNum.charAt(0))) { 
				METHODS_POSITVE_SIGN = true;
				BIG_NUMBER[0] = '+';
				
				for (int i = 0; i< strNum.length(); i++) {
					//fill the array of Bytes (BigInt)
					String charAsString = String.valueOf(strNum.charAt(i));
					BIG_NUMBER[i] = Byte.parseByte(charAsString);
					
				}
			}
			else {
				
				throw new BigIntException("Enter a valid number!");
			}	
			
			
				
				if (BIG_NUMBER[0] > 9 ) {// first element is a sign
					
					for (int i =1; i < BIG_NUMBER.length; i++) {
						if(BIG_NUMBER[i] > 0) {//first occurrence of a non-zero number
							RESULT_BIG_NUMBER = new byte[BIG_NUMBER.length - i];
						for (int j =i; j < BIG_NUMBER.length; j++) {
							
							RESULT_BIG_NUMBER[j-i] = BIG_NUMBER[j];
						}
						break;
					}
					}
				}
				else {// first element is a number
					
					for (int i =0; i < BIG_NUMBER.length; i++) {
						if(BIG_NUMBER[i] > 0) {//first occurrence of a non-zero number
							RESULT_BIG_NUMBER = new byte[BIG_NUMBER.length - i];
						for (int j =i; j < BIG_NUMBER.length; j++) {
							
							RESULT_BIG_NUMBER[j-i] = BIG_NUMBER[j];
						}
						break;
					}
					}
				}
	
	}
	
	
	
	
	
	public int compareTo(BigInt num) {
		
		if (this.METHODS_POSITVE_SIGN == true && num.METHODS_POSITVE_SIGN == false) {
			
			return 1;
		}
		else if(this.METHODS_POSITVE_SIGN == false && num.METHODS_POSITVE_SIGN == true) {
			return -1;
		}
		//have the same sign
		else if(this.RESULT_BIG_NUMBER.length > num.RESULT_BIG_NUMBER.length) {//checking for the the size after getting rid of the leading zeros
			return 1;
			
		}
		else if(this.RESULT_BIG_NUMBER.length < num.RESULT_BIG_NUMBER.length) {//checking for the the size after getting rid of the leading zeros
			return -1;
			
		}
		else {
			
			for (int i=0; i< RESULT_BIG_NUMBER.length; i++) {
				
				if(this.RESULT_BIG_NUMBER[i]> num.RESULT_BIG_NUMBER[i]) {
					
					return 1;
				}
				else if(this.RESULT_BIG_NUMBER[i] < num.RESULT_BIG_NUMBER[i]) {
					
					return -1;
				}
				else {
					continue;
				}
				
			}
			return 0; // in this case the two BigInt have the same value
		}
		
	}
	
	
	
	
	
	
	public boolean equals(BigInt num) {
		
		if (this.compareTo(num) == 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
	public String toString() {
		
		
		StringBuilder stringBuilder = new StringBuilder();
		
		
		if (!this.METHODS_POSITVE_SIGN) {
			stringBuilder.append("-");
		}
		for (int i : RESULT_BIG_NUMBER) {
		    stringBuilder.append(i);
		}

		String strRep = stringBuilder.toString();
		
		return strRep;
	}
	
	
	
	
	
	public BigInt clone() {
        byte[] copyOfBigNumber = Arrays.copyOf(BIG_NUMBER, BIG_NUMBER.length);
        byte[] copyOfResultBigNumber = Arrays.copyOf(RESULT_BIG_NUMBER, RESULT_BIG_NUMBER.length);

        // Create and return a new BigInt object with the copied values
        BigInt clonedBigInt = new BigInt("");
        clonedBigInt.BIG_NUMBER = copyOfBigNumber;
        clonedBigInt.RESULT_BIG_NUMBER = copyOfResultBigNumber;
        clonedBigInt.METHODS_POSITVE_SIGN = this.METHODS_POSITVE_SIGN;
        clonedBigInt.strRep = this.strRep;

        return clonedBigInt;
    }
	
	
	//this method takes two BigInt object and return their sum as an another BigInt object,
	//( array of bytes ,sign).
	public BigInt add(BigInt num) throws BigIntException {
		
		try {
		if (num == null) {} 
			
			
		}catch(NullPointerException e) {
			throw new BigIntException("Enter valid numbers!");
		}
		if(this.METHODS_POSITVE_SIGN == true && num.METHODS_POSITVE_SIGN == true) {//both have +ve sign
			
			BigInt num3 = new BigInt();
			num3.RESULT_BIG_NUMBER = addPositives(this,num);
			num3.METHODS_POSITVE_SIGN = true;
			return num3;
		}
		else if(this.METHODS_POSITVE_SIGN == false && num.METHODS_POSITVE_SIGN == false) {//both have -ve sign
			
			BigInt num3 = new BigInt();
			num3.RESULT_BIG_NUMBER = addPositives(this,num);
			num3.METHODS_POSITVE_SIGN = false;
			return num3;
		}
		else {
			
			if(this.METHODS_POSITVE_SIGN == false) {
				this.METHODS_POSITVE_SIGN = true;
				if(this.compareTo(num) == -1) {
					BigInt num3 = new BigInt();
					num3.RESULT_BIG_NUMBER = subtractPositives(num, this);
					num3.METHODS_POSITVE_SIGN = true;
					this.METHODS_POSITVE_SIGN = false;
					return num3;
				}
				else {
					this.METHODS_POSITVE_SIGN = true;
					BigInt num3 = new BigInt();
					num3.RESULT_BIG_NUMBER = subtractPositives(this, num);
					num3.METHODS_POSITVE_SIGN = false;
					this.METHODS_POSITVE_SIGN = false;
					return num3;
				}
				
				
				
			}
			else {
				
					num.METHODS_POSITVE_SIGN = true;
					if(this.compareTo(num) == -1) {
						BigInt num3 = new BigInt();
						num3.RESULT_BIG_NUMBER = subtractPositives(num, this);
						num3.METHODS_POSITVE_SIGN = false;
						num.METHODS_POSITVE_SIGN = false;
						return num3;
					}
					else {
						this.METHODS_POSITVE_SIGN = true;
						BigInt num3 = new BigInt();
						num3.RESULT_BIG_NUMBER = subtractPositives(this, num);
						num3.METHODS_POSITVE_SIGN = true;
						num.METHODS_POSITVE_SIGN = false;
						return num3;
					}
			
		}
	}
}
	
	//this method takes two BigInt object and return their difference as an another BigInt object,
	//( array of bytes ,sign).
	public BigInt subtract(BigInt num) throws BigIntException{
		try {
			if (num == null) {}
				
				
			}catch(NullPointerException e) {
				throw new BigIntException("Enter valid numbers!");
			}
	
		if (this.METHODS_POSITVE_SIGN == true && num.METHODS_POSITVE_SIGN == true) {
			BigInt num3 = new BigInt();
			if(this.compareTo(num) == -1) {
				num3.RESULT_BIG_NUMBER = subtractPositives(num, this); //switch numbers
				num3.METHODS_POSITVE_SIGN = false;
			}
			else {
				num3.RESULT_BIG_NUMBER = subtractPositives(this, num); //normal order
				num3.METHODS_POSITVE_SIGN = true;
			}
			
			return num3;
        }else if (this.METHODS_POSITVE_SIGN == false && num.METHODS_POSITVE_SIGN == false) {
        	BigInt num3 = new BigInt();
        	if(this.compareTo(num) == -1) {
				num3.RESULT_BIG_NUMBER = subtractPositives(num, this); //switch numbers
				num3.METHODS_POSITVE_SIGN = true;
			}
			else {
				num3.RESULT_BIG_NUMBER = subtractPositives(this, num); 
				num3.METHODS_POSITVE_SIGN = false;
			}
            return num3;
        }
		else {//here subtraction turns into addition and the sings change
            if (this.METHODS_POSITVE_SIGN == false) {
                
                BigInt num3 = new BigInt();
                num3.RESULT_BIG_NUMBER = addPositives(this, num);
                num3.METHODS_POSITVE_SIGN = false;
                return num3;
            } else {
                
                BigInt num3 = new BigInt();
                num3.RESULT_BIG_NUMBER = addPositives(this, num);
                num3.METHODS_POSITVE_SIGN = true; 
                return num3;
            }
        }
	}
	
	
	
	
	
	
	
	
	//Helper method for the add method
	public static byte[] addPositives(BigInt num1, BigInt num2) {
		
		
		byte[] resultArray;
		int len1 = num1.RESULT_BIG_NUMBER.length;
		int len2 = num2.RESULT_BIG_NUMBER.length;
		int maxLen = Math.max(len1, len2);
		
		resultArray = new byte[maxLen + 1];
		int carry = 0;
		int sum = 0;
			for (int index = 0; index < maxLen; index++) {
				
				sum = carry;
				
				if (index < len1) {
					sum += num1.RESULT_BIG_NUMBER[len1 - 1 - index];
				}
				if (index < len2) {
					sum += num2.RESULT_BIG_NUMBER[len2 - 1 - index];
				}
				
				if (sum > 9) {
					
					carry = sum / 10; // Calculate carry for the next iteration
					sum %= 10;// Keep only the last digit in the current cell

				}
				else {
					carry = 0;
				}
				
				resultArray[maxLen - index] = (byte) sum; // cast the iterative result to a byte
			}
			
			
			if(carry > 0) {
				
				resultArray[0] = (byte) carry;
				return resultArray;
			}
			else {// If there's no carry in the most significant digit, remove the extra leading zero
				
				byte[] finalResult = new byte[resultArray.length - 1];
				System.arraycopy(resultArray, 1, finalResult, 0, finalResult.length);
				

	            return finalResult;
			}
			

	}
	
	//Helper method for the subtract method
	public static byte[] subtractPositives(BigInt num1, BigInt num2) {
	    // Ensure num1 is greater than or equal to num2
	    if (num1.compareTo(num2) == -1) {
	        BigInt temp = num1;
	        num1 = num2;
	        num2 = temp;
	    }

	    byte[] resultArray;
	    int len1 = num1.RESULT_BIG_NUMBER.length;
	    int len2 = num2.RESULT_BIG_NUMBER.length;
	    int maxLen = Math.max(len1, len2);

	    resultArray = new byte[maxLen]; // The result array length is the same as the longer input array
	    int borrow = 0;

	    for (int index = 0; index < maxLen; index++) {
	        int diff = 0; // Initialize diff to 0
	        if (index < len1) {
	            diff += num1.RESULT_BIG_NUMBER[len1 - 1 - index];
	        }
	        if (index < len2) {
	            diff -= num2.RESULT_BIG_NUMBER[len2 - 1 - index];
	        }
	        diff -= borrow; // Add the borrow

	        if (diff < 0) {
	            borrow = 1; // There's a borrow for the next iteration
	            diff += 10; // Add 10 to the current digit
	        } else {
	            borrow = 0;
	        }

	        resultArray[maxLen - 1 - index] = (byte) diff; // Store the digit in the result
	    }

	    // Remove leading zeros in the result
	    int firstNonZero = 0;
	    while (firstNonZero < resultArray.length && resultArray[firstNonZero] == 0) {
	        firstNonZero++;
	    }

	    if (firstNonZero == resultArray.length) {
	        return new byte[]{0}; // The result is zero
	    } else {
	        byte[] finalResult = new byte[resultArray.length - firstNonZero];
	        System.arraycopy(resultArray, firstNonZero, finalResult, 0, finalResult.length);


	        return finalResult;
	    }
	}
	
	
	public void printInfo() {
		
		if(this.METHODS_POSITVE_SIGN == true) {
			
			System.out.print("+");
			
		}
		else {
			
			System.out.print("-");
		}
		
		for(int i : this.RESULT_BIG_NUMBER) {
			
			System.out.print(i);
		}
		
		System.out.println();
	}

}
