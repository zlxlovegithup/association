package com.feng.vo;

import com.feng.entity.Passage;
import com.feng.entity.PassageType;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by zlx on 2020/05/08.
 */
@Data
public class PassagePageVo {
    private PassageType passageType;
    private PageInfo<Passage> passagePageInfo;

    public PassagePageVo(PassageType passageType, PageInfo<Passage> passagePageInfo) {
        this.passageType = passageType;
        this.passagePageInfo = passagePageInfo;
    }

    public PassagePageVo() {
    }
}
