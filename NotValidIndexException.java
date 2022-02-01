/**
 * This Exception file dealing with customized exception NotValidIndexException. 
 * That exception would be thrown when <code>lookup()</code> is called. A message
 * "This index is not valid." would be displayed when exception is thrown. 
 * @author dxl746
 */
public class NotValidIndexException extends Exception{
    public NotValidIndexException(String message){
        super(message);
    }
}
