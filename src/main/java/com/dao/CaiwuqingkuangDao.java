package com.dao;

import com.entity.CaiwuqingkuangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CaiwuqingkuangVO;
import com.entity.view.CaiwuqingkuangView;


/**
 * 财务情况
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
public interface CaiwuqingkuangDao extends BaseMapper<CaiwuqingkuangEntity> {
	
	List<CaiwuqingkuangVO> selectListVO(@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);
	
	CaiwuqingkuangVO selectVO(@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);
	
	List<CaiwuqingkuangView> selectListView(@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);

	List<CaiwuqingkuangView> selectListView(Pagination page,@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);

	
	CaiwuqingkuangView selectView(@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CaiwuqingkuangEntity> wrapper);



}
