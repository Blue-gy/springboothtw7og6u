package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 财务情况
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
@TableName("caiwuqingkuang")
public class CaiwuqingkuangEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public CaiwuqingkuangEntity() {
		
	}
	
	public CaiwuqingkuangEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 统计日期
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date tongjiriqi;
	
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
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：统计日期
	 */
	public void setTongjiriqi(Date tongjiriqi) {
		this.tongjiriqi = tongjiriqi;
	}
	/**
	 * 获取：统计日期
	 */
	public Date getTongjiriqi() {
		return tongjiriqi;
	}
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
