package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChangdiweixiuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChangdiweixiuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChangdiweixiuView;


/**
 * 场地维修
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
public interface ChangdiweixiuService extends IService<ChangdiweixiuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChangdiweixiuVO> selectListVO(Wrapper<ChangdiweixiuEntity> wrapper);
   	
   	ChangdiweixiuVO selectVO(@Param("ew") Wrapper<ChangdiweixiuEntity> wrapper);
   	
   	List<ChangdiweixiuView> selectListView(Wrapper<ChangdiweixiuEntity> wrapper);
   	
   	ChangdiweixiuView selectView(@Param("ew") Wrapper<ChangdiweixiuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChangdiweixiuEntity> wrapper);

   	

}

