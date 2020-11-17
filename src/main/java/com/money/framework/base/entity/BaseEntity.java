package com.money.framework.base.entity;

import com.money.framework.base.exception.PandabusSpecException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;

public class BaseEntity extends OperationalEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4553006705113237397L;

    /**
     * This
     *
     * @param name
     * @return
     */
    public Object get(String name) {
        try {
            Field field = getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(this);
        } catch (Exception e) {
            getLogger().error(e.getMessage(), e);
        }
        return null;
    }

    public String getString(String name) {
        String rtnString = "";
        Object obj = get(name);
        if (obj instanceof String) {
            rtnString = (String) obj;
        }
        return rtnString;
    }

    public void set(String name, Object value) {
        try {
            Field field = getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(this, value);
        } catch (SecurityException e) {
            getLogger().error(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            getLogger().error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            getLogger().error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            getLogger().error(e.getMessage(), e);
        }
    }

    /**
     * 获取column中的字符串，不允许为空
     *
     * @param row
     * @param column
     * @param blankErrMsg 为空错误提示
     * @return
     */
    public String getCellStringVal(Row row, char column, String blankErrMsg) {
        int rowNumber = row.getRowNum() + 1;
        String result = getCellStringVal(row, column);
        if (StringUtils.isEmpty(result)) {
            throw new PandabusSpecException(String.format("行%s:%s", rowNumber, blankErrMsg));
        }
        return result;
    }

    /**
     * 获取column中的字符串  可返回null
     *
     * @param row
     * @param column
     * @return
     */
    public String getCellStringVal(Row row, char column) {
        String result = null;
        try {
            Cell cell = row.getCell(column - 'A');
            if (cell != null) {
                result = cell.getStringCellValue();
            }
        } catch (Exception ex) {
            throw new PandabusSpecException(String.format("读取行%s,列%s 错误.", row.getRowNum() + 1, column));
        }
        return result;
    }

    /**
     * 获取column中的数字，
     * 可以空 或者 非空且可以转换为数字
     *
     * @param row
     * @param column
     * @return
     */
    public Integer getCellIntVal(Row row, char column) {
        int rowNum = row.getRowNum() + 1;
        Integer result = null;
        String stringVal = getCellStringVal(row, column);
        if (StringUtils.isNotBlank(stringVal)) {
            if (!StringUtils.isNumeric(stringVal)) {
                throw new PandabusSpecException(String.format("行%s:(%s)无法转换成数字", rowNum, stringVal));
            }
            result = Integer.parseInt(stringVal);
        }

        return result;
    }

    /**
     * 获取column中的数字，
     * 非空且可以转换为数字
     *
     * @param row
     * @param column
     * @param blankErrMsg
     * @return
     */
    public Integer getCellIntVal(Row row, char column, String blankErrMsg) {
        int rowNumber = row.getRowNum() + 1;
        Integer result = getCellIntVal(row, column);
        if (Objects.isNull(result)) {
            throw new PandabusSpecException(String.format("行%s:%s", rowNumber, blankErrMsg));
        }
        return result;
    }
}
