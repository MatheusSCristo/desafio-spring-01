package com.challengespring1.utils;

public class ValidateFields {

    public static boolean validateUpdateFields(Object object) {
        if (object == null) return false;
        else if ((object instanceof String))
            return !((String) object).isEmpty();
        return true;
    }

}
