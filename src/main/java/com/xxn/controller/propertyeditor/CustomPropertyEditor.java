package com.xxn.controller.propertyeditor;



import org.springframework.cglib.core.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 席雪楠 on 2017/10/24.
 */
public class CustomPropertyEditor implements org.springframework.core.convert.converter.Converter<String,Date> {

    @Override
    public Date convert(String source) {
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

}

