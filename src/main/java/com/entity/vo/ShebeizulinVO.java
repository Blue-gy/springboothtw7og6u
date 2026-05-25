package com.entity.vo;

import com.entity.ShebeizulinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 设备租赁
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
public class ShebeizulinVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 库存数量
	 */
	
	private Integer kucunshuliang;
		
	/**
	 * 设备图片
	 */
	
	private String shebeitupian;
		
	/**
	 * 设备简介
	 */
	
	private String shebeijianjie;
		
	/**
	 * 租赁价格
	 */
	
	private Integer zulinjiage;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：库存数量
	 */
	 
	public void setKucunshuliang(Integer kucunshuliang) {
		this.kucunshuliang = kucunshuliang;
	}
	
	/**
	 * 获取：库存数量
	 */
	public Integer getKucunshuliang() {
		return kucunshuliang;
	}
				
	
	/**
	 * 设置：设备图片
	 */
	 
	public void setShebeitupian(String shebeitupian) {
		this.shebeitupian = shebeitupian;
	}
	
	/**
	 * 获取：设备图片
	 */
	public String getShebeitupian() {
		return shebeitupian;
	}
				
	
	/**
	 * 设置：设备简介
	 */
	 
	public void setShebeijianjie(String shebeijianjie) {
		this.shebeijianjie = shebeijianjie;
	}
	
	/**
	 * 获取：设备简介
	 */
	public String getShebeijianjie() {
		return shebeijianjie;
	}
				
	
	/**
	 * 设置：租赁价格
	 */
	 
	public void setZulinjiage(Integer zulinjiage) {
		this.zulinjiage = zulinjiage;
	}
	
	/**
	 * 获取：租赁价格
	 */
	public Integer getZulinjiage() {
		return zulinjiage;
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
			
}
