package com.feng.dto;

import com.feng.entity.Activity;
import com.feng.entity.Activity;
import com.feng.entity.ActivityType;
import lombok.Data;

/**
 * Created by zlx on 2020/5/8.
 */
@Data
public class ActivityTypeDto extends Activity{
    private ActivityType activityType;
}
