package com.task.util;

import java.util.List;

import Jama.Matrix;

import com.task.entity.DetectResult;
import com.task.entity.UserFacialFeatures;

/**
 * Created by hucheng on 2017/12/11.
 */

public class FeatureCompareUtil {
    /**
     * 提前将数据库的特征值字符串转换为比对能使用的double[]给UserFeature传参
     * @param userFeature
     * @param dataBaseFeature
     */
    public static void dataStrToDoubleArray (UserFacialFeatures userFeature, String dataBaseFeature){

        userFeature.setProcessFeatures(getFaceFeature(dataBaseFeature));

    }

    /**
     * 数据比对使用的是double[]类型，将字符串转为比对能使用的double数据
     * @param stringfeature
     * @return
     */
    public static double[] getFaceFeature(String stringfeature){

        String[] sSecond = stringfeature.split(",");
        double[][] feature2 = new double[1][sSecond.length];

        for(int i = 0; i < sSecond.length; i++){
            double  valuefeature = Double.valueOf(sSecond[i]);
            feature2[0][i] = valuefeature;
        }

        return feature2[0];
    }

    /**
     * 比对人脸把数据库里面的所有人脸特征放入到集合，进行比对
     * @param userFeatureList  需要从数据获取，获取提前读取到内存，这形势可以服务端自行决定
     * @param compareFeature  上传的需要进行比对的字符串
     * @return
     */
    public static DetectResult isCompareFeatureresult(List<UserFacialFeatures> userFeatureList, String compareFeature ){

        //需要进行比对的人脸特征值获取
        double[] compareFeaturearray = getFaceFeature(compareFeature);

        //以下要使用矩阵乘积，故使用二维定义，需要比对的人脸矩阵
        double[][] doubleExtractFeatures;
        doubleExtractFeatures = new double[compareFeaturearray.length][1];
        float total = 0.0f;
        Double sqrtTotal = 0.0;
        for(int i = 0; i < compareFeaturearray.length;i++){
            float temp = (float) (compareFeaturearray[i] * compareFeaturearray[i]);
            total += temp;
        }

        sqrtTotal = Math.sqrt(Double.valueOf(total));
        for(int i = 0; i < compareFeaturearray.length;i++){
            doubleExtractFeatures[i][0] = (compareFeaturearray[i]/sqrtTotal);
        }

        //数据库所有的人脸特征数据
        double allFeatures[][] = new double[userFeatureList.size()][compareFeaturearray.length];
        //给要乘积的allFeatures赋值
        for(int i = 0; i < userFeatureList.size(); i++){
            try {
				double[] userFeatureValues = userFeatureList.get(i).getProcessFeatures();
				for(int j = 0; j < compareFeaturearray.length; j++){

				    allFeatures[i][j] = userFeatureList.get(i).getProcessFeatures()[j];
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }

        //矩阵乘积运算 乘积出来temp是一列分数值，找最大的分数值
        Jama.Matrix matrix1 = new Jama.Matrix(allFeatures,userFeatureList.size(),compareFeaturearray.length);
        Jama.Matrix matrix2 = new Jama.Matrix(doubleExtractFeatures,compareFeaturearray.length,1);
        Jama.Matrix matrix3 = matrix1.times(matrix2);
        double[][] temp  = matrix3.getArray();
        double maxScore = 0.0;
        int userCount = 0;

        for(int i = 0; i < temp.length; i++)
        {
            for(int j = 0; j <1; j++){
                if(temp[i][0] >maxScore){
                    maxScore = temp[i][0];
                    userCount = i;
                }
                System.out.println(("value++++++++" + temp[i][j]));
            }
        }

        //相似度大于0.6就认为成功 userCount 是List索引号，  maxScore是相似度最大分数
        if(maxScore > 0.6){
            return new DetectResult(userCount, true,maxScore);
        }else {
            return new DetectResult(userCount, false,maxScore);
        }

    }
}

