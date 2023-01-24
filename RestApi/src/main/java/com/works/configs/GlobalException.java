package com.works.configs;

import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, errorParse(ex.getFieldErrors()) );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    private List errorParse(List<FieldError> fieldErrors) {
        List ls = new ArrayList();
        for( FieldError error : fieldErrors ) {
            Map hm = new HashMap();
            hm.put("field", error.getField());
            hm.put("message", error.getDefaultMessage());
            ls.add(hm);
        }
        return ls;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity sQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors,  ex.getMessage() );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

}
