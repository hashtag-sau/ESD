package com.saurabh.myrestaurant.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
//above line ensures that the superclasses (RuntimeException) equals() and hashCode() methods are invoked as part of the current class's equals() and hashCode() methods.
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;
}
