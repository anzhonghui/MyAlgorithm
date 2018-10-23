package com.qk.myleetcode.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * code is far away from bug with the animal protecting
 * @Description : k-nearest neighbours KNN
 * K最邻近算法：是一种用于分类和回归的非参数统计方法，最近邻居法采用向量空间模型来分类，概念为相同类别的案例，彼此的相似度高，而可以借由计算与已知类别案例之相似度，来评估未知类别案例可能的分类。
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月22日
 */
public class BreadPresale {

	/**
	 * @Description：假如你想开个小小的面包店，每天都做新鲜的面包，需要根据如下的一组特征预测当天该考多少面包：
	 * 天气指数：1~5（1表示天气很糟，5表示天气很好）
	 * 是不是周末或者节假日（周末或节假日为1，否则为0）
	 * 有没有活动（1表示没有，0表示有）
	 * @param args
	 */
	public static void main(String[] args) {
		// 今天的情况
		Feature feature0 = new Feature();
		feature0.setWeather(4);
		feature0.setFestival(1);
		feature0.setActivity(0);

		// 特征
		List<Feature> list = new ArrayList<>();
		Feature feature = new Feature(5, 1, 0, 300);
		Feature feature2 = new Feature(3, 1, 1, 225);
		Feature feature3 = new Feature(1, 1, 0, 75);
		Feature feature4 = new Feature(4, 0, 1, 200);
		Feature feature5 = new Feature(4, 0, 0, 150);
		Feature feature6 = new Feature(2, 0, 0, 50);
		list.add(feature);
		list.add(feature2);
		list.add(feature3);
		list.add(feature4);
		list.add(feature5);
		list.add(feature6);

		// 特征抽取，找邻近值
		List<Integer> list2 = new ArrayList<>();
		for (Feature temp : list) {
			// 利用向量求解相似程度
			if (Math.sqrt((temp.getWeather() - feature0.getWeather()) * (temp.getWeather() - feature0.getWeather())
					+ (temp.getFestival() - feature0.getFestival()) * (temp.getFestival() - feature0.getFestival())
					+ (temp.getActivity() - feature0.getActivity())
							* (temp.getActivity() - feature0.getActivity())) <= 2) {
				list2.add(temp.getBreadSalesVolume());
			}
		}

		// 回滚，预测结果
		// 计算平均值
		int sum = 0;
		for (Integer integer : list2) {
			sum += integer;
		}
		System.out.println("相近的特征值：" + list2.toString());
		System.out.println("预测的均值为：" + sum / (list2.size() * 1.0));
	}

}

class Feature {
	// 天气
	private Integer weather;
	// 节假日
	private Integer festival;
	// 活动
	private Integer activity;
	// 面包销售数量
	private Integer breadSalesVolume;

	public Integer getWeather() {
		return weather;
	}

	public void setWeather(Integer weather) {
		this.weather = weather;
	}

	public Integer getFestival() {
		return festival;
	}

	public void setFestival(Integer festival) {
		this.festival = festival;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getBreadSalesVolume() {
		return breadSalesVolume;
	}

	public void setBreadSalesVolume(Integer breadSalesVolume) {
		this.breadSalesVolume = breadSalesVolume;
	}

	public Feature(Integer weather, Integer festival, Integer activity, Integer breadSalesVolume) {
		super();
		this.weather = weather;
		this.festival = festival;
		this.activity = activity;
		this.breadSalesVolume = breadSalesVolume;
	}

	public Feature() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Feature [weather=" + weather + ", festival=" + festival + ", activity=" + activity
				+ ", breadSalesVolume=" + breadSalesVolume + "]";
	}

}
