package com.feng.dto;

import com.feng.entity.File;
import com.feng.entity.Passage;
import com.feng.entity.PassageType;
import lombok.Data;

import java.util.List;

/**
 * Created by zlx on 2020/4/28.
 */
@Data
public class PassageFileDto extends Passage{
    private List<File> fileList;
}
