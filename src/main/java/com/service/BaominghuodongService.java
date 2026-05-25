package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BaominghuodongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BaominghuodongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BaominghuodongView;


/**
 * 报名活动
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
public interface BaominghuodongService extends IService<BaominghuodongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BaominghuodongVO> selectListVO(Wrapper<BaominghuodongEntity> wrapper);
   	
   	BaominghuodongVO selectVO(@Param("ew") Wrapper<BaominghuodongEntity> wrapper);
   	
   	List<BaominghuodongView> selectListView(Wrapper<BaominghuodongEntity> wrapper);
   	
   	BaominghuodongView selectView(@Param("ew") Wrapper<BaominghuodongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BaominghuodongEntity> wrapper);

   	

}

