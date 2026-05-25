package com.dao;

import com.entity.ChangdiweixiuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChangdiweixiuVO;
import com.entity.view.ChangdiweixiuView;


/**
 * 场地维修
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
public interface ChangdiweixiuDao extends BaseMapper<ChangdiweixiuEntity> {
	
	List<ChangdiweixiuVO> selectListVO(@Param("ew") Wrapper<ChangdiweixiuEntity> wrapper);
	
	ChangdiweixiuVO selectVO(@Param("ew") Wrapper<ChangdiweixiuEntity> wrapper);
	
	List<ChangdiweixiuView> selectListView(@Param("ew") Wrapper<ChangdiweixiuEntity> wrapper);

	List<ChangdiweixiuView> selectListView(Pagination page,@Param("ew") Wrapper<ChangdiweixiuEntity> wrapper);

	
	ChangdiweixiuView selectView(@Param("ew") Wrapper<ChangdiweixiuEntity> wrapper);
	

}
