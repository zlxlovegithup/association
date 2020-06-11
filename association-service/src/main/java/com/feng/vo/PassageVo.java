package com.feng.vo;

import com.feng.entity.Passage;
import com.feng.entity.PassageType;
import lombok.Data;

import java.util.List;

/**
 * Created by zlx on 2020/05/08.
 */
@Data
public class PassageVo{
    private PassageType passageType;
    private List<Passage> passageList;

    public PassageVo(PassageType passageType, List<Passage> passageList) {
        this.passageType = passageType;
        this.passageList = passageList;
    }

    public PassageVo() {
    }
}
