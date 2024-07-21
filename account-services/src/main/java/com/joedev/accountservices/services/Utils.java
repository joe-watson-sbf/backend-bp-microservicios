package com.joedev.accountservices.services;

import com.joedev.accountservices.exceptions.BusinessException;

public class Utils {
    public static void requireNonNull(Object obj, String message) {
        if (obj == null) {
            throw new BusinessException(message);
        }
    }
}
