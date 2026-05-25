package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShebeiweixiujiluEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShebeiweixiujiluVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShebeiweixiujiluView;


/**
 * 设备维修记录
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
public interface ShebeiweixiujiluService extends IService<ShebeiweixiujiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShebeiweixiujiluVO> selectListVO(Wrapper<ShebeiweixiujiluEntity> wrapper);
   	
   	ShebeiweixiujiluVO selectVO(@Param("ew") Wrapper<ShebeiweixiujiluEntity> wrapper);
   	
   	List<ShebeiweixiujiluView> selectListView(Wrapper<ShebeiweixiujiluEntity> wrapper);
   	
   	ShebeiweixiujiluView selectView(@Param("ew") Wrapper<ShebeiweixiujiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShebeiweixiujiluEntity> wrapper);

   	

}

