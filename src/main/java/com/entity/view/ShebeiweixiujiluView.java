package com.entity.view;

import com.entity.ShebeiweixiujiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 设备维修记录
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
@TableName("shebeiweixiujilu")
public class ShebeiweixiujiluView  extends ShebeiweixiujiluEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ShebeiweixiujiluView(){
	}
 
 	public ShebeiweixiujiluView(ShebeiweixiujiluEntity shebeiweixiujiluEntity){
 	try {
			BeanUtils.copyProperties(this, shebeiweixiujiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
