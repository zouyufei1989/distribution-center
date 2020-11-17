package com.money.framework.base.excel;

import com.money.framework.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class BaseExportExcelView<T> extends AbstractXlsxView {

    protected abstract String[] buildHeaders();

    protected abstract List<Function<T, String>> buildContentFuncs();

    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rowIndex = 0;
        Map<String, Object> reportContentMap = (Map<String, Object>) model.get("reportContentMap");
        List<T> list = (List<T>) reportContentMap.get("data");
        String name = reportContentMap.get("name").toString();

        Sheet sheet = ExcelUtils.createSheet(workbook, name);
        CellStyle headStyle = ExcelUtils.createHeaderStyle(workbook);
        CellStyle contentStyle = ExcelUtils.createContentStyle(workbook);

        String[] headers = buildHeaders();
        ExcelUtils.createHeaderCells(sheet, rowIndex++, headStyle, headers);

        List<Function<T, String>> funcs = buildContentFuncs();
        for (int i = 0; i < list.size(); i++) {
            createContentCells(sheet, rowIndex++, contentStyle, list.get(i), funcs);
        }

        setAutoColumnWidth(sheet, funcs.size());
        ExcelUtils.writeExcel(workbook, request, response, name);
    }

    private void setAutoColumnWidth(Sheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    void createContentCells(Sheet sheet, Integer rowIndex, CellStyle style, T content, List<Function<T, String>> funcs) {
        Row totalSheetRow = sheet.createRow(rowIndex);
        for (int i = 0; i < funcs.size(); i++) {
            Cell cell = totalSheetRow.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(funcs.get(i).apply(content));
        }
    }

    protected String formatPercent(Double percent) {
        return (double) Math.round(percent * 100) / 100 + "%";
    }

    protected String toString(Object val) {
        if (Objects.isNull(val)) {
            return StringUtils.EMPTY;
        }
        return val.toString();
    }
}
