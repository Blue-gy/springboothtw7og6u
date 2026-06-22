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
 * 场地信息
 * 数据库通用操作实体类（普通增删改查）
 * @author
 * @email
 * @date 2030-02-26 18:34:49
 */
@TableName("changdixinxi")
public class ChangdixinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ChangdixinxiEntity() {

	}

	public ChangdixinxiEntity(T t) {
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
	 * 场地名称
	 */

	private String changdimingcheng;

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
	 * 设置：场地名称
	 */
	public void setChangdimingcheng(String changdimingcheng) {
		this.changdimingcheng = changdimingcheng;
	}
	/**
	 * 获取：场地名称
	 */
	public String getChangdimingcheng() {
		return changdimingcheng;
	}
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
