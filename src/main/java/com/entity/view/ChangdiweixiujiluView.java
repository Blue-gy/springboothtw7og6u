package com.entity.view;

import com.entity.ChangdiweixiujiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 场地维修记录
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
@TableName("changdiweixiujilu")
public class ChangdiweixiujiluView  extends ChangdiweixiujiluEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChangdiweixiujiluView(){
	}
 
 	public ChangdiweixiujiluView(ChangdiweixiujiluEntity changdiweixiujiluEntity){
 	try {
			BeanUtils.copyProperties(this, changdiweixiujiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
