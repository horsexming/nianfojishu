package com.task.entity;

import java.io.Serializable;

/**
 * Created by hucheng on 2017/12/11.
 */

public class DetectResult  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    //假如成功用户最新的索引号
    private Integer userFeatureIndex = -1;
    //返回识别是否成功
    private Boolean result;
    private double score;//人脸相似度

    public DetectResult(Integer userFeatureIndex, Boolean result, double score) {
        this.userFeatureIndex = userFeatureIndex;
        this.result = result;
        this.score = score;
    }

    public Integer getUserFeatureIndex() {
        return userFeatureIndex;
    }

    public void setUserFeatureIndex(Integer userFeatureIndex) {
        this.userFeatureIndex = userFeatureIndex;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
