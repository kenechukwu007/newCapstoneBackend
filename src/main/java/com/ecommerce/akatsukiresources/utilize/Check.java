package com.ecommerce.akatsukiresources.utilize;

import javax.validation.constraints.NotNull;

public class Check {
    public static boolean isPresent(Object object){
        return object != null;
    }
}
