package com.task.entity;

import java.io.Serializable;

/***
 * 入职培训详细内容
 * 
 * @author 刘培
 * 
 */
public class JoinTrainDetails  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String trainContent;//内容
	private String speaker;//主讲人
	private String trainDate;//培训时间
	private String testScore;//考试成绩
	private String ownSigStatus;//本人是否签字
	private String speakerSigStatus;//讲师是否签字
	private JoinTrain joinTrain;

	public JoinTrainDetails() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}

	public String getTestScore() {
		return testScore;
	}

	public void setTestScore(String testScore) {
		this.testScore = testScore;
	}

	public String getOwnSigStatus() {
		return ownSigStatus;
	}

	public void setOwnSigStatus(String ownSigStatus) {
		this.ownSigStatus = ownSigStatus;
	}

	public String getSpeakerSigStatus() {
		return speakerSigStatus;
	}

	public void setSpeakerSigStatus(String speakerSigStatus) {
		this.speakerSigStatus = speakerSigStatus;
	}

	public JoinTrain getJoinTrain() {
		return joinTrain;
	}

	public void setJoinTrain(JoinTrain joinTrain) {
		this.joinTrain = joinTrain;
	}
}
