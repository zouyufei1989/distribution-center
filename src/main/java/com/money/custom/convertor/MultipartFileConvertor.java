package com.money.custom.convertor;

import com.money.framework.base.entity.ExcelMultipartFile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class MultipartFileConvertor implements Converter<CommonsMultipartFile, ExcelMultipartFile> {

    @Override
    public ExcelMultipartFile convert(CommonsMultipartFile source) {
        return new ExcelMultipartFile(source.getFileItem());
    }
}
