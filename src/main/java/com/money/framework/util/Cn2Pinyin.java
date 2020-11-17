package com.money.framework.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Cn2Pinyin represents 汉字转换为拼音，英文字符不变
 *
 * @author fengjiachun
 * @version $Id$
 */
public class Cn2Pinyin {

    final static Logger logger = LoggerFactory.getLogger(Cn2Pinyin.class);

    /**
     * This 汉字转换为汉语拼音
     *
     * @param chineseCharacters
     * @param acronymy          true:只要汉语拼音首字母 false:转换全部
     * @return
     */
    public static String convert(String chineseCharacters, boolean acronymy) {
        String pinyinName = "";
        char[] nameChar = chineseCharacters.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char element : nameChar) {
            if (element > 128 && element != '（' && element != '）') {
                try {
                    if (acronymy) {
                        pinyinName += PinyinHelper.toHanyuPinyinStringArray(element, defaultFormat)[0].charAt(0);
                    } else {
                        pinyinName += PinyinHelper.toHanyuPinyinStringArray(element, defaultFormat)[0];
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            } else {
                pinyinName += element;
            }
        }
        return pinyinName;
    }

    public static void main(String[] args) {
        String test = "高新园区-高新园区";
        System.out.println(convert(test, false));
        System.out.println(convert(test, true));
    }
}
