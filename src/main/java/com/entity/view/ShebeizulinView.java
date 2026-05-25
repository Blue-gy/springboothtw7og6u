package com.entity.view;

import com.entity.ShebeizulinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 设备租赁
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
@TableName("shebeizulin")
public class ShebeizulinView  extends ShebeizulinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShebeizulinView(){
	}
 
 	public ShebeizulinView(ShebeizulinEntity shebeizulinEntity){
 	try {
			BeanUtils.copyProperties(this, shebeizulinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
