package com.entity.vo;

import com.entity.ChangdiyuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 场地预约
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
public class ChangdiyuyueVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 场地图片
	 */
	
	private String changditupian;
		
	/**
	 * 场地介绍
	 */
	
	private String changdijieshao;
		
	/**
	 * 场地地址
	 */
	
	private String changdidizhi;
		
	/**
	 * 场地设施
	 */
	
	private String changdisheshi;
		
	/**
	 * 开放时间
	 */
	
	private String kaifangshijian;
		
	/**
	 * 场地价格
	 */
	
	private Integer changdijiage;
		
	/**
	 * 时间段
	 */
	
	private String shijianduan;
		
	/**
	 * 预约时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date yuyueshijian;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
		
	/**
	 * 手机
	 */
	
	private String shouji;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
				
	
	/**
	 * 设置：场地图片
	 */
	 
	public void setChangditupian(String changditupian) {
		this.changditupian = changditupian;
	}
	
	/**
	 * 获取：场地图片
	 */
	public String getChangditupian() {
		return changditupian;
	}
				
	
	/**
	 * 设置：场地介绍
	 */
	 
	public void setChangdijieshao(String changdijieshao) {
		this.changdijieshao = changdijieshao;
	}
	
	/**
	 * 获取：场地介绍
	 */
	public String getChangdijieshao() {
		return changdijieshao;
	}
				
	
	/**
	 * 设置：场地地址
	 */
	 
	public void setChangdidizhi(String changdidizhi) {
		this.changdidizhi = changdidizhi;
	}
	
	/**
	 * 获取：场地地址
	 */
	public String getChangdidizhi() {
		return changdidizhi;
	}
				
	
	/**
	 * 设置：场地设施
	 */
	 
	public void setChangdisheshi(String changdisheshi) {
		this.changdisheshi = changdisheshi;
	}
	
	/**
	 * 获取：场地设施
	 */
	public String getChangdisheshi() {
		return changdisheshi;
	}
				
	
	/**
	 * 设置：开放时间
	 */
	 
	public void setKaifangshijian(String kaifangshijian) {
		this.kaifangshijian = kaifangshijian;
	}
	
	/**
	 * 获取：开放时间
	 */
	public String getKaifangshijian() {
		return kaifangshijian;
	}
				
	
	/**
	 * 设置：场地价格
	 */
	 
	public void setChangdijiage(Integer changdijiage) {
		this.changdijiage = changdijiage;
	}
	
	/**
	 * 获取：场地价格
	 */
	public Integer getChangdijiage() {
		return changdijiage;
	}
				
	
	/**
	 * 设置：时间段
	 */
	 
	public void setShijianduan(String shijianduan) {
		this.shijianduan = shijianduan;
	}
	
	/**
	 * 获取：时间段
	 */
	public String getShijianduan() {
		return shijianduan;
	}
				
	
	/**
	 * 设置：预约时间
	 */
	 
	public void setYuyueshijian(Date yuyueshijian) {
		this.yuyueshijian = yuyueshijian;
	}
	
	/**
	 * 获取：预约时间
	 */
	public Date getYuyueshijian() {
		return yuyueshijian;
	}
				
	
	/**
	 * 设置：用户账号
	 */
	 
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
				
	
	/**
	 * 设置：手机
	 */
	 
	public void setShouji(String shouji) {
		this.shouji = shouji;
	}
	
	/**
	 * 获取：手机
	 */
	public String getShouji() {
		return shouji;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
			
}
