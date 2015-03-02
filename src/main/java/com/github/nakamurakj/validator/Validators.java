/*
 * Copyright 2015 https://github.com/nakamurakj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.nakamurakj.validator;

import static org.apache.commons.lang3.StringUtils.*;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.DateValidator;

/**
 * Validator
 */
public final class Validators {

    /** 真偽型 */
    private static final Boolean[] BOOLEAN_VALUES = {Boolean.TRUE, Boolean.FALSE};
    /** yes/no */
    private static final String[] YES_OR_NO_VALUES = {"yes", "no"};

    /** 正規表現：全角カナ */
    private static final String KATAKANA_REGEX = "^[\u30A0-\u30FF]+$";
    /** 正規表現：半角カナ */
    private static final String HALF_KATAKANA_REGEX = "^[\uFF65-\uFF9F]+$";
    /** 正規表現：全角かな */
    private static final String HIRAGANA_REGEX = "^[\u3040-\u309F]+$";;
    /** 正規表現：電話番号 */
    /* 正規表現はこちらを参考(http://lightmaterial.blogspot.jp/2007/10/blog-post_14.html) */
    private final static String TEL_NO_REGEX =
            "(?!^(090|080|070|050))(?=^\\d{2,5}?-\\d{1,4}?-\\d{4}$)[\\d-]{12}|(?=^(090|080|070|050)-\\d{4}-\\d{4}$)[\\d-]{13}";
    /** 正規表現：郵便番号 */
    private final static String ZIP_CODE_REGEX = "[0-9]{3}+-[0-9]{4}+";
    /** 正規表現：半角数値 */
    private final static String HALF_NUMBER_REGEX = "^[0-9]*$";

    private static final String SPACE = " ";
    private static final String EM_SPACE = "　";
    private static final String EM_LONG_MARKS = "ー";

    /**
     * private constractors
     */
    private Validators() {
        // ignore
    }


    /**
     * 数値型文字列の形式チェック
     *
     * @param string 文字列
     * @param min 最小桁
     * @param max 最大桁
     * @return 数値型文字列の場合<code>true</code>
     */
    public static boolean isNumberString(String string, int min, int max) {
        if (string != null && string.length() >= min && string.length() <= max) {
            try {
            	long value = Long.parseLong(string);
                return Long.MIN_VALUE <= value && value <= Long.MAX_VALUE;
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return false;
    }


    /**
     * 半角数値型文字列の形式チェック
     *
     * @param string 文字列
     * @return 半角数値型文字列の場合<code>true</code>
     */
    public static boolean isHalfNumber(String string) {
        return isPatternMatch(string, HALF_NUMBER_REGEX);
    }



    /**
     * 電話番号の形式チェック
     *
     * @param tel 電話番号
     * @return 電話番号形式の場合<code>true</code>
     */
    public static boolean isTelNo(String tel, String regex) {
        if (regex == null) {
            return isPatternMatch(tel, TEL_NO_REGEX);
        }
        return isPatternMatch(tel, regex);
    }


    /**
     * 郵便番号の形式チェック
     *
     * @param zip 郵便番号
     * @return 郵便番号形式の場合<code>true</code>
     */
    public static boolean isZipCode(String zip, String regex) {
        if (regex == null) {
            return isPatternMatch(zip, ZIP_CODE_REGEX);
        }
        return isPatternMatch(zip, regex);
    }

    /**
     * 正規表現に合った形式チェック
     *
     * @param string 文字列
     * @param regex 正規表現
     * @return 正規表現に合った形式の場合<code>true</code>
     */
    public static boolean isPatternMatch(String string, String regex) {
        if (string != null && regex != null) {
            Matcher matcher = Pattern.compile(regex).matcher(string);
            return matcher.matches();
        }
        return false;
    }

    /**
     * 文字列真偽型チェック
     *
     * @param string 文字列
     * @return 真偽型の場合<code>true</code>
     */
    public static boolean isBoolean(String string) {
        if (string != null) {
            for (Boolean value : BOOLEAN_VALUES) {
                if (value.toString().equals(string.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * yes/noチェック
     *
     * @param string 文字列
     * @return yes/noの場合<code>true</code>
     */
    public static boolean isYesOrNo(String string) {
        if (string != null) {
            for (String value : YES_OR_NO_VALUES) {
                if (value.toLowerCase().equals(string.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Charset有効チェック
     *
     * @param string 文字列
     * @return 有効の場合<code>true</code>
     */
    public static boolean isCharsetSupported(String string) {
        try {
            return Charset.isSupported(string);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 日付フォーマットチェック
     *
     * @param string 文字列
     * @param pattern 日付フォーマット
     * @return 日付フォーマットの場合<code>true</code>
     */
    public static boolean isDateFormat(String string, String pattern) {
        if (string != null && pattern != null) {
            return DateValidator.getInstance().validate(string, pattern) != null;
        }
        return false;
    }

    /**
     * 半角カタカナチェック
     *
     * @param string 文字列
     * @return 半角カタカナの場合<code>true</code>
     */
    public static boolean isHalfKatakana(String string) {
        return isPatternMatch(string, HALF_KATAKANA_REGEX);
    }

    /**
     * 全角カタカナチェック
     *
     * @param string 文字列
     * @param space スペース("　")を許容する。
     * @param halfSpace 半角スペースを許容する。
     * @param longMarks 長音("ー")を許容する。
     * @return 全角カナの場合<code>true</code>
     */
    public static boolean isKatakana(String string, boolean space, boolean halfSpace,
            boolean longMarks) {
        if (string != null) {
            String check = removeMatcher(string, space, halfSpace, longMarks);
            return isPatternMatch(check, KATAKANA_REGEX);
        }
        return false;
    }

    /**
     * ひらがなチェック
     *
     * @param string 文字列
     * @param space スペース("　")を許容する。
     * @param halfSpace 半角スペースを許容する。
     * @param longMarks 長音("ー")を許容する。
     * @return ひらがなの場合<code>true</code>
     */
    public static boolean isHiragana(String string, boolean space, boolean halfSpace,
            boolean longMarks) {
        if (string != null) {
            String check = removeMatcher(string, space, halfSpace, longMarks);
            return isPatternMatch(check, HIRAGANA_REGEX);
        }
        return false;
    }

    /**
     * マッチする際に不要なスペース、長音の文字列を場合分けして除去する。
     *
     * @param string 文字列
     * @param space 全角スペースを取り除く
     * @param halfSpace 半角スペースを取り除く
     * @param longMarks 長音を取り除く
     * @return 除去結果の文字列
     */
    private static String removeMatcher(String string, boolean space, boolean halfSpace,
            boolean longMarks) {
        String check = string;
        if (space) {
            // 全角スペースは取り除く
            check = replace(check, EM_SPACE, EMPTY);
        }
        if (halfSpace) {
            // 半角スペースは取り除く
            check = replace(check, SPACE, EMPTY);
        }
        if (longMarks) {
            // 長音を取り除く
            check = replace(check, EM_LONG_MARKS, EMPTY);
        }
        return check;
    }

}
