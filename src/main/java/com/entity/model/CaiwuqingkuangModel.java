package com.entity.model;

import com.entity.CaiwuqingkuangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 财务情况
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
public class CaiwuqingkuangModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用户消费
	 */
	
	private Integer huiyuanxiaofei;
		
	/**
	 * 场地租赁费用
	 */
	
	private Integer changdizulinfeiyong;
		
	/**
	 * 活动收入
	 */
	
	private Integer huodongshouru;
		
	/**
	 * 总收入
	 */
	
	private Integer zongshouru;
				
	
	/**
	 * 设置：用户消费
	 */
	 
	public void setHuiyuanxiaofei(Integer huiyuanxiaofei) {
		this.huiyuanxiaofei = huiyuanxiaofei;
	}
	
	/**
	 * 获取：用户消费
	 */
	public Integer getHuiyuanxiaofei() {
		return huiyuanxiaofei;
	}
				
	
	/**
	 * 设置：场地租赁费用
	 */
	 
	public void setChangdizulinfeiyong(Integer changdizulinfeiyong) {
		this.changdizulinfeiyong = changdizulinfeiyong;
	}
	
	/**
	 * 获取：场地租赁费用
	 */
	public Integer getChangdizulinfeiyong() {
		return changdizulinfeiyong;
	}
				
	
	/**
	 * 设置：活动收入
	 */
	 
	public void setHuodongshouru(Integer huodongshouru) {
		this.huodongshouru = huodongshouru;
	}
	
	/**
	 * 获取：活动收入
	 */
	public Integer getHuodongshouru() {
		return huodongshouru;
	}
				
	
	/**
	 * 设置：总收入
	 */
	 
	public void setZongshouru(Integer zongshouru) {
		this.zongshouru = zongshouru;
	}
	
	/**
	 * 获取：总收入
	 */
	public Integer getZongshouru() {
		return zongshouru;
	}
			
}
