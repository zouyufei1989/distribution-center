package com.money.framework.base.excel;

import com.money.framework.util.ExcelUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseUploadExcelView extends AbstractXlsView {

    public final static Integer MAX_ROWS = 10000;

    protected final String SHEET_NAME = "sheet";

    protected abstract String[] buildHeaders();

    protected abstract List<Consumer<Map<String, Object>>> buildValidations();

    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rowIndex = 0;
        Map<String, Object> reportContentMap = (Map<String, Object>) model.get("reportContentMap");
        String name = reportContentMap.get("name").toString();

        Sheet sheet = ExcelUtils.createSheet(workbook, name);
        CellStyle headStyle = ExcelUtils.createHeaderStyle(workbook);
        CellStyle contentStyle = ExcelUtils.createContentStyle(workbook);

        String[] headers = buildHeaders();
        ExcelUtils.createHeaderCells(sheet, rowIndex++, headStyle, headers);

        reportContentMap.put("workbook", workbook);
        reportContentMap.put(SHEET_NAME, sheet);

        List<Consumer<Map<String, Object>>> validations = buildValidations();
        for (int i = 0; i < validations.size(); i++) {
            validations.get(i).accept(reportContentMap);
        }

        setAllCellFormatAsText(workbook, sheet);
        hideTempDataSheet(workbook, 1);
        ExcelUtils.writeExcel(workbook, request, response, name);
    }

    private void setAllCellFormatAsText(Workbook workbook, Sheet sheet) {
        CellStyle textStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            sheet.setDefaultColumnStyle(i, textStyle);
            sheet.autoSizeColumn(i);
        }
    }

    protected <T> Map<String, String> convertListToMap(List<T> src, Function<T, String> getKeyFunc, Function<T, String> getValueFunc) {
        Map<String, String> result = new LinkedHashMap<>();

        try {
            if (!CollectionUtils.isEmpty(src)) {
                for (T item : src) {
                    result.put(getKeyFunc.apply(item), getValueFunc.apply(item));
                }
            }
        } catch (Exception ex) {
            logger.error("集合转换map反射失败", ex);
        }

        return result;
    }

    private static void hideTempDataSheet(Workbook workbook, int start) {
        for (int i = start; i < workbook.getNumberOfSheets(); i++) {
            workbook.setSheetHidden(i, true);
        }
    }
}
