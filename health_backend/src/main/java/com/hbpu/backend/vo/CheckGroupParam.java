package com.hbpu.backend.vo;

import com.hbpu.common.pojo.CheckGroup;

/**
 * <b><code>CheckGroupParam</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2023/6/4 17:17.
 *
 * @author 皮
 */
public class CheckGroupParam {
    private CheckGroup checkGroup;
    private Integer[] checkItemIds;

    public CheckGroup getCheckGroup() {
        return checkGroup;
    }

    public void setCheckGroup(CheckGroup checkGroup) {
        this.checkGroup = checkGroup;
    }

    public Integer[] getCheckItemIds() {
        return checkItemIds;
    }

    public void setCheckItemIds(Integer[] checkItemIds) {
        this.checkItemIds = checkItemIds;
    }
}
