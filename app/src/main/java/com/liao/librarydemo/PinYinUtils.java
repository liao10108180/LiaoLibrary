package com.liao.librarydemo;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-13
 * @Description: description class
 */
public class PinYinUtils<T> {

    private HanyuPinyinOutputFormat pinYinFormat;
    private List<T> mData;

    private List<String> mPinYinList = new ArrayList<>();

    private MatchContent<T> matchContent;

    public PinYinUtils(List<T> data) {
        pinYinFormat = new HanyuPinyinOutputFormat();
        pinYinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        mData = data;

    }

    private void getPinYinList() {
        if (mData == null || mData.size() < 1) {
            return;
        }

        for (T data : mData) {
            try {
                mPinYinList.add(getDataPinShorts(matchContent.matchContent(data)));
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 拼音缩写搜索对呀数据集
     *
     * @param str
     * @return
     */
    public List<T> matchList(String str) {

        if (TextUtils.isEmpty(str.trim())) {
            return null;
        }
        List<T> resultData = new ArrayList<>();
        str = str.toLowerCase();

        //判断首字符是否为中文
        char c = str.charAt(0);
        if (c >= 0x4E00 && c <= 0x29FA5) {
            //字符串查询
            for (T d : mData) {
                if (matchContent.matchContent(d).startsWith(str)) {
                    resultData.add(d);

                }
            }

        } else {
            //拼音缩写查询

            for (int i = 0; i < mPinYinList.size(); i++) {

                if (mPinYinList.get(i).startsWith(str) && i < mData.size()) {
                    resultData.add(mData.get(i));

                }
            }
        }


        return resultData;
    }

    /**
     * 获取拼音缩写
     *
     * @param str
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private String getDataPinShorts(String str) throws BadHanyuPinyinOutputFormatCombination {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        StringBuilder shorts = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            String[] strArray = PinyinHelper.toHanyuPinyinStringArray(c, pinYinFormat);

            if (strArray == null) {
                //strArray直接返回
                shorts.append(c);

            } else {
                //取拼音第一个字母
                shorts.append(strArray[0].substring(0, 1));
            }

        }
        return shorts.toString().toLowerCase();
    }

    public static class Builder {
        private PinYinUtils pinYinUtils;

        public <T> Builder(@NonNull List<T> data) {
            pinYinUtils = new PinYinUtils(data);

        }

        public <T> Builder matchContent(MatchContent<T> matchContent) {
            pinYinUtils.matchContent = matchContent;
            return this;
        }


        public <T> PinYinUtils<T> build() {
            pinYinUtils.getPinYinList();
            return pinYinUtils;
        }
    }

    /**
     * Item内容
     *
     * @param <T>
     */
    public interface MatchContent<T> {
        String matchContent(T data);
    }
}
