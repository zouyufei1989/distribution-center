package com.money.custom.convertor;

import com.money.framework.util.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateConvertor implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        return DateUtils.parse(source, "yyyy-MM-dd");
    }
}
