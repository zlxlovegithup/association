package com.feng.vo;

import com.feng.entity.Activity;
import com.feng.entity.ActivityType;
import lombok.Data;

import java.util.List;

/**
 * Created by zlx on 2020/05/08.
 */
@Data
public class ActivityVo {
    private ActivityType activityType;
    private List<Activity> activityList;

    public ActivityVo(ActivityType activityType, List<Activity> activityList) {
        this.activityType = activityType;
        this.activityList = activityList;
    }

    public ActivityVo() {
    }
}
