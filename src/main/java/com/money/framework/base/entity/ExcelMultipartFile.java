package com.money.framework.base.entity;

import com.money.framework.base.excel.BaseUploadExcelView;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * 改写自CommonMultipartFile
 */
public class ExcelMultipartFile extends CommonsMultipartFile {

    protected static final Log logger = LogFactory.getLog(ExcelMultipartFile.class);

    private OperationalEntity operationalEntity = new OperationalEntity();


    public OperationalEntity getOperationalEntity() {
        return operationalEntity;
    }

    public void setOperationalEntity(OperationalEntity operationalEntity) {
        this.operationalEntity = operationalEntity;
    }

    /**
     * Create an instance wrapping the given FileItem.
     *
     * @param fileItem the FileItem to wrap
     */
    public ExcelMultipartFile(FileItem fileItem) {
        super(fileItem);

        Assert.isTrue(StringUtils.equals("xls", getExtension()), "只支持xls文件上传");
    }

    String getExtension() {
        String extention = getOriginalFilename().split("\\.")[1];
        return extention;
    }

    public <T> List<T> readExcelFile(BiFunction<Row, OperationalEntity, T> mappingFunc) throws IOException {
        Assert.notNull(getOperationalEntity(), "该账号无所属公司，不可导入操作");
        Assert.notNull(getOperationalEntity().getGroupId(), "该账号无所属公司，不可导入操作");

        List<T> content = new LinkedList<>();

        try (InputStream inputStream = getFileItem().getInputStream()) {
            Workbook wb = new HSSFWorkbook(inputStream);
            Sheet sheet = wb.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            logger.info("excel行数：" + lastRowNum);
            Assert.isTrue(lastRowNum <= BaseUploadExcelView.MAX_ROWS, "上传文件最大行数不可超过" + BaseUploadExcelView.MAX_ROWS);

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                logger.info(i);
                if (row == null || row.getCell(0) == null || StringUtils.isEmpty(row.getCell(0).getStringCellValue())) {
                    break;
                }

                T item = mappingFunc.apply(row, getOperationalEntity());
                content.add(item);
            }
        }

        return content;
    }

}
