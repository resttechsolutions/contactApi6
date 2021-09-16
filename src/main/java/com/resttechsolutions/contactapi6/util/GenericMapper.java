package com.resttechsolutions.contactapi6.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class GenericMapper {

    public static <T, R> R map(T t, Class<R> rClass, ModelMapper mapper){
        R r = mapper.map(t, rClass);

        return r;
    }

    public static <T, R>List<R> mapCollection(List<T> t, Class<R> rClass, ModelMapper mapper){
        return t.stream().map(item -> mapper.map(item, rClass)).collect(Collectors.toList());
    }
}
