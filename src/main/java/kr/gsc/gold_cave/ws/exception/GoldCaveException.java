package kr.gsc.gold_cave.ws.exception;

public class GoldCaveException extends RuntimeException {
    private ErrorCode code;
    
    public GoldCaveException(ErrorCode code, String message) {
        super(message);
        
        this.code = code;
    }
    
    public ErrorCode getErrorCode() {
        return this.code;
    }
}