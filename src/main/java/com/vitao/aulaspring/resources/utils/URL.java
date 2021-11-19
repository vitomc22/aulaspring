package com.vitao.aulaspring.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e){
            return "";
        }

    }

    public static List<Integer> decodeIntList(String s) {
        String[] vet = s.split(",");        //split pega uma String e corta em pedaços de acordo com o parâmetro passado, nesse caso ","
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vet.length; i++) {
            list.add(Integer.parseInt(vet[i]));

        }
        return list;
        //usando lambda ficaria assim
        //return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}
