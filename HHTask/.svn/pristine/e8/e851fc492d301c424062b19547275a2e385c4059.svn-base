package com.task.ServerImpl;

import com.task.Dao.TotalDao;
import com.task.Server.JoinTrainServer;
import com.task.entity.JoinTrain;
import com.task.entity.JoinTrainDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 入职培训Server层实现类
 * 
 * @author 刘培
 * 
 */
public class JoinTrainServerImpl implements JoinTrainServer {

	private TotalDao totalDao;

	public JoinTrainServerImpl() {
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加入职培训记录
	public boolean addJoinTrain(JoinTrain joinTrain, JoinTrainDetails jtDetails) {
		if (joinTrain != null) {
			if (jtDetails != null) {
				String[] trainContent = jtDetails.getTrainContent().split(",");// 内容
				String[] speaker = jtDetails.getSpeaker().split(",");// 主讲人
				String[] trainDate = jtDetails.getTrainDate().split(",");// 培训时间
				String[] testScore = jtDetails.getTestScore().split(",");// 考试成绩
				String[] ownSigStatus = jtDetails.getOwnSigStatus().split(",");// 本人是否签字
				String[] speakerSigStatus = jtDetails.getSpeakerSigStatus()
						.split(",");// 讲师是否签字
				if (trainContent != null && trainContent.length > 0
						&& speaker != null && speaker.length > 0
						&& trainDate != null && trainDate.length > 0
						&& testScore != null && testScore.length > 0
						&& ownSigStatus != null && ownSigStatus.length > 0
						&& speakerSigStatus != null
						&& speakerSigStatus.length > 0) {

					Set<JoinTrainDetails> joinTDetailsSet = new HashSet<JoinTrainDetails>();
					for (int i = 0; i < trainContent.length; i++) {
						JoinTrainDetails joinTrainDetails = new JoinTrainDetails();// 创建培训记录详细
						joinTrainDetails
								.setTrainContent(trainContent[i].trim());// 内容
						joinTrainDetails.setSpeaker(speaker[i].trim());// 主讲人
						joinTrainDetails.setTrainDate(trainDate[i].trim());// 培训时间
						joinTrainDetails.setTestScore(testScore[i].trim());// 考试成绩
						joinTrainDetails
								.setOwnSigStatus(ownSigStatus[i].trim());// 本人签字
						if (speakerSigStatus[i].trim().equals("是")) {
							joinTrainDetails.setSpeakerSigStatus(speaker[i]);// 主讲人签字
						} else {
							joinTrainDetails
									.setSpeakerSigStatus(speakerSigStatus[i]
											.trim());// 主讲人签字
						}

						joinTrainDetails.setJoinTrain(joinTrain);// 所属培训记录
						joinTDetailsSet.add(joinTrainDetails);
					}
					joinTrain.setJoinTDetails(joinTDetailsSet);
				}
			}
			return totalDao.save(joinTrain);
		} else {
			return false;
		}
	}

	// 查询入职培训记录
	@SuppressWarnings("unchecked")
	public List findJoinTrainByUid(Integer userId) {
		if (userId != null && userId.intValue() > 0) {
			String hql = "from JoinTrain j where j.user.id=?";
			return totalDao.query(hql, new Object[] { userId });
		} else {
			return null;
		}
	}

	// 删除入职培训记录以及明细
	public boolean delJoinTrain(JoinTrain jointrain) {
		if (jointrain != null) {
			return totalDao.delete(jointrain);
		}
		return false;
	}

	// 根据id查询培训记录
	public JoinTrain findJoinTrainById(Integer id) {
		if (id != null && id > 0) {
			return (JoinTrain) totalDao.getObjectById(JoinTrain.class, id);
		}
		return null;
	}
}
