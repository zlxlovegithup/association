package com.feng.dto;

import com.feng.entity.Passage;
import com.feng.entity.PassageType;
import lombok.Data;

/**
 * Created by zlx on 2020/5/8.
 */
@Data
public class PassageTypeDto extends Passage{
    private PassageType passageType;
}
