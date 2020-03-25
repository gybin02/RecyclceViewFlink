package com.vv.common.recycleview;

/**
 * Desc:
 * <p>
 * Date: 2020/3/25
 * Copyright: Copyright (c) 2010 - 2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @param
 * @author zhengxiaobin
 */
public class CategoryEn {
    public String title = "";
    public String[] subTitle = {"", ""};
    public String icon = "";
    public boolean isFocus = false;
    /**
     * 是否已经翻转
     */
    public boolean isFLip = false;
    public int type = TYPE_BIG;

    public static final int TYPE_BIG = 0;
    public static final int TYPE_SMALL = 1;


    public CategoryEn(String title, String[] subTitle, int type) {
        this.title = title;
        this.subTitle = subTitle;
        this.type = type;
    }

    public boolean isBig() {
        return type == TYPE_BIG;
    }
}
