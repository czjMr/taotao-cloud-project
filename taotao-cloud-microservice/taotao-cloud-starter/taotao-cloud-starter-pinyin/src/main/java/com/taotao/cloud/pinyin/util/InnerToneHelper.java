package com.taotao.cloud.pinyin.util;


import com.taotao.cloud.common.utils.io.FileStreamUtils;
import com.taotao.cloud.common.utils.lang.ObjectUtils;
import com.taotao.cloud.common.utils.lang.StringUtils;
import com.taotao.cloud.pinyin.constant.PinyinConst;
import com.taotao.cloud.pinyin.model.CharToneInfo;
import com.taotao.cloud.pinyin.model.ToneItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InnerToneHelper {

    private InnerToneHelper(){}

    /**
     * 存放对应的拼音声调信息
     */
    private static final Map<Character, ToneItem> TONE_ITEM_MAP;

    static {
        TONE_ITEM_MAP = new HashMap<>(34);

        List<String> allLines = FileStreamUtils.readAllLines(PinyinConst.PINYIN_DICT_TONE_SYSTEM);
        for(String line : allLines) {
            String[] strings = line.split(StringUtils.BLANK);
            ToneItem item = ToneItem.of(strings[0].charAt(0), Integer.parseInt(strings[1]));

            TONE_ITEM_MAP.put(strings[2].charAt(0), item);
        }
    }

    /**
     * 获取对应的拼音信息
     * @param c 拼音
     * @return 结果
     */
    public static ToneItem getToneItem(final char c) {
        return TONE_ITEM_MAP.get(c);
    }

    /**
     * 获取对应的声调信息
     * @param tone 拼音信息
     * @return 声调信息
     */
    public static CharToneInfo getCharToneInfo(final String tone) {
        CharToneInfo charToneInfo = new CharToneInfo();
        charToneInfo.setIndex(-1);

        int length = tone.length();
        for(int i = 0; i < length; i++) {
            char currentChar = tone.charAt(i);
            ToneItem toneItem = InnerToneHelper.getToneItem(currentChar);

            if (ObjectUtils.isNotNull(toneItem)) {
                charToneInfo.setToneItem(toneItem);
                charToneInfo.setIndex(i);
                break;
            }
        }

        return charToneInfo;
    }

}
