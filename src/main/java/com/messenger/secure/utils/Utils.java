package com.messenger.secure.utils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

    private Utils(){} // classe utilitaire -> ne s'instancie pas
    
    public static <T> List<T> minus(List<T> a, List<T> b) {
        return a.stream().filter(Predicate.not(b::contains)).collect(Collectors.toList());
    }

    public static <T> List<T> intersect(List<T> a, List<T> b) {
        return a.stream().filter(b::contains).collect(Collectors.toList());
    }

}
