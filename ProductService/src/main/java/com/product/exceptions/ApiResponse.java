package com.product.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@ToString
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus statusCode;
    private ZonedDateTime zonedDateTime;
    private String message;
   private ApiResponse(){
       this.zonedDateTime=ZonedDateTime.now();
   }
   public ApiResponse(HttpStatus status,String message){
       this();
       this.statusCode=status;
       this.message=message;
   }
}
