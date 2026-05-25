package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaiwuqingkuangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaiwuqingkuangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaiwuqingkuangView;


/**
 * 财务情况
 *
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
public interface CaiwuqingkuangService extends IService<CaiwuqingkuangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaiwuqingkuangVO> selectListVO(Wrapper<CaiwuqingkuangEntity> wrapper);
   	
   	CaiwuqingkuangVO selectVO(@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);
   	
   	List<CaiwuqingkuangView> selectListView(Wrapper<CaiwuqingkuangEntity> wrapper);
   	
   	CaiwuqingkuangView selectView(@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaiwuqingkuangEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<CaiwuqingkuangEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<CaiwuqingkuangEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<CaiwuqingkuangEntity> wrapper);



}

