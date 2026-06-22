package com.entity.vo;

import com.entity.KechengxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 课程信息
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
public class KechengxinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 课程难度
	 */
	
	private String kechengnandu;
		
	/**
	 * 适合人群
	 */
	
	private String shiherenqun;
		
	/**
	 * 课程简介
	 */
	
	private String kechengjianjie;
		
	/**
	 * 教练
	 */
	
	private String jiaolian;
		
	/**
	 * 课程图片
	 */
	
	private String kechengtupian;
		
	/**
	 * 上课时间
	 */
	
	private String shangkeshijian;
		
	/**
	 * 上课地点
	 */
	
	private String shangkedidian;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
	/**
	 * 人数限制
	 */
	
	private Integer renshuxianzhi;

	/**
	 * 已预约人数
	 */
	
	private Integer yiyuyuerenshu;
				
	
	/**
	 * 设置：课程难度
	 */
	 
	public void setKechengnandu(String kechengnandu) {
		this.kechengnandu = kechengnandu;
	}
	
	/**
	 * 获取：课程难度
	 */
	public String getKechengnandu() {
		return kechengnandu;
	}
				
	
	/**
	 * 设置：适合人群
	 */
	 
	public void setShiherenqun(String shiherenqun) {
		this.shiherenqun = shiherenqun;
	}
	
	/**
	 * 获取：适合人群
	 */
	public String getShiherenqun() {
		return shiherenqun;
	}
				
	
	/**
	 * 设置：课程简介
	 */
	 
	public void setKechengjianjie(String kechengjianjie) {
		this.kechengjianjie = kechengjianjie;
	}
	
	/**
	 * 获取：课程简介
	 */
	public String getKechengjianjie() {
		return kechengjianjie;
	}
				
	
	/**
	 * 设置：教练
	 */
	 
	public void setJiaolian(String jiaolian) {
		this.jiaolian = jiaolian;
	}
	
	/**
	 * 获取：教练
	 */
	public String getJiaolian() {
		return jiaolian;
	}
				
	
	/**
	 * 设置：课程图片
	 */
	 
	public void setKechengtupian(String kechengtupian) {
		this.kechengtupian = kechengtupian;
	}
	
	/**
	 * 获取：课程图片
	 */
	public String getKechengtupian() {
		return kechengtupian;
	}
				
	
	/**
	 * 设置：上课时间
	 */
	 
	public void setShangkeshijian(String shangkeshijian) {
		this.shangkeshijian = shangkeshijian;
	}
	
	/**
	 * 获取：上课时间
	 */
	public String getShangkeshijian() {
		return shangkeshijian;
	}
				
	
	/**
	 * 设置：上课地点
	 */
	 
	public void setShangkedidian(String shangkedidian) {
		this.shangkedidian = shangkedidian;
	}
	
	/**
	 * 获取：上课地点
	 */
	public String getShangkedidian() {
		return shangkedidian;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}

	/**
	 * 设置：人数限制
	 */
	public void setRenshuxianzhi(Integer renshuxianzhi) {
		this.renshuxianzhi = renshuxianzhi;
	}

	/**
	 * 获取：人数限制
	 */
	public Integer getRenshuxianzhi() {
		return renshuxianzhi;
	}

	/**
	 * 设置：已预约人数
	 */
	public void setYiyuyuerenshu(Integer yiyuyuerenshu) {
		this.yiyuyuerenshu = yiyuyuerenshu;
	}

	/**
	 * 获取：已预约人数
	 */
	public Integer getYiyuyuerenshu() {
		return yiyuyuerenshu;
	}
			
}
