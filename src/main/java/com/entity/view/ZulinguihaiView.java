package com.entity.view;

import com.entity.ZulinguihaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 租赁归还
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
@TableName("zulinguihai")
public class ZulinguihaiView  extends ZulinguihaiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZulinguihaiView(){
	}
 
 	public ZulinguihaiView(ZulinguihaiEntity zulinguihaiEntity){
 	try {
			BeanUtils.copyProperties(this, zulinguihaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
