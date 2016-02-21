package com.movies;

public class IdGenerator {

    public static String generate(Product product){
        String result="Err";
        if (product instanceof Movie)  result="MOV";
        else if (product instanceof Game)  result="GAM";
        else if (product instanceof Book)  result="BOO";
        result += product.hashCode();
        return result;

    }
}
