package com.entity.view;

import com.entity.ChangdiweixiuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 场地维修
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
@TableName("changdiweixiu")
public class ChangdiweixiuView  extends ChangdiweixiuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChangdiweixiuView(){
	}
 
 	public ChangdiweixiuView(ChangdiweixiuEntity changdiweixiuEntity){
 	try {
			BeanUtils.copyProperties(this, changdiweixiuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
