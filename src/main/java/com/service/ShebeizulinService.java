package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShebeizulinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShebeizulinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShebeizulinView;


/**
 * 设备租赁
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
public interface ShebeizulinService extends IService<ShebeizulinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShebeizulinVO> selectListVO(Wrapper<ShebeizulinEntity> wrapper);
   	
   	ShebeizulinVO selectVO(@Param("ew") Wrapper<ShebeizulinEntity> wrapper);
   	
   	List<ShebeizulinView> selectListView(Wrapper<ShebeizulinEntity> wrapper);
   	
   	ShebeizulinView selectView(@Param("ew") Wrapper<ShebeizulinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShebeizulinEntity> wrapper);

   	

}

