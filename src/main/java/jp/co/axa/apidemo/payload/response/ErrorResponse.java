package jp.co.axa.apidemo.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    public String errorCode;
    public String message;
}
