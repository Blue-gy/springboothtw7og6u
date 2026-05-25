package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BisaibaomingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BisaibaomingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BisaibaomingView;


/**
 * 比赛报名
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
public interface BisaibaomingService extends IService<BisaibaomingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BisaibaomingVO> selectListVO(Wrapper<BisaibaomingEntity> wrapper);
   	
   	BisaibaomingVO selectVO(@Param("ew") Wrapper<BisaibaomingEntity> wrapper);
   	
   	List<BisaibaomingView> selectListView(Wrapper<BisaibaomingEntity> wrapper);
   	
   	BisaibaomingView selectView(@Param("ew") Wrapper<BisaibaomingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BisaibaomingEntity> wrapper);

   	

}

