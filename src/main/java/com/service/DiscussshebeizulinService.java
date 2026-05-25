package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussshebeizulinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussshebeizulinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussshebeizulinView;


/**
 * 设备租赁评论表
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:52
 */
public interface DiscussshebeizulinService extends IService<DiscussshebeizulinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussshebeizulinVO> selectListVO(Wrapper<DiscussshebeizulinEntity> wrapper);
   	
   	DiscussshebeizulinVO selectVO(@Param("ew") Wrapper<DiscussshebeizulinEntity> wrapper);
   	
   	List<DiscussshebeizulinView> selectListView(Wrapper<DiscussshebeizulinEntity> wrapper);
   	
   	DiscussshebeizulinView selectView(@Param("ew") Wrapper<DiscussshebeizulinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussshebeizulinEntity> wrapper);

   	

}

