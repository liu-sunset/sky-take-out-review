package exception;

import entity.User;

public class UserException extends RuntimeException{
    public UserException(String msg){
        super(msg);
    }
}
