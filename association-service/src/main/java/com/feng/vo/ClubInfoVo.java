package com.feng.vo;

import com.feng.entity.Club;
import com.feng.entity.ClubType;
import com.feng.entity.File;
import lombok.Data;

import java.util.List;

/**
 * Created by zlx on 2020/05/08.
 */
@Data
public class ClubInfoVo extends Club {
    private ClubType clubType;
    private List<File> fileList;
}
