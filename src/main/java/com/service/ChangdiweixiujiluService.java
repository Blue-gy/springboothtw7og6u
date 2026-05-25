package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChangdiweixiujiluEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChangdiweixiujiluVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChangdiweixiujiluView;


/**
 * 场地维修记录
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
public interface ChangdiweixiujiluService extends IService<ChangdiweixiujiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChangdiweixiujiluVO> selectListVO(Wrapper<ChangdiweixiujiluEntity> wrapper);
   	
   	ChangdiweixiujiluVO selectVO(@Param("ew") Wrapper<ChangdiweixiujiluEntity> wrapper);
   	
   	List<ChangdiweixiujiluView> selectListView(Wrapper<ChangdiweixiujiluEntity> wrapper);
   	
   	ChangdiweixiujiluView selectView(@Param("ew") Wrapper<ChangdiweixiujiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChangdiweixiujiluEntity> wrapper);

   	

}

