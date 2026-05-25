package com.entity.view;

import com.entity.DiscussbisaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 比赛评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-02-26 18:34:52
 */
@TableName("discussbisai")
public class DiscussbisaiView  extends DiscussbisaiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussbisaiView(){
	}
 
 	public DiscussbisaiView(DiscussbisaiEntity discussbisaiEntity){
 	try {
			BeanUtils.copyProperties(this, discussbisaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
