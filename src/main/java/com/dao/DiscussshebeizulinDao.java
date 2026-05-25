package com.dao;

import com.entity.DiscussshebeizulinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussshebeizulinVO;
import com.entity.view.DiscussshebeizulinView;


/**
 * 设备租赁评论表
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:52
 */
public interface DiscussshebeizulinDao extends BaseMapper<DiscussshebeizulinEntity> {
	
	List<DiscussshebeizulinVO> selectListVO(@Param("ew") Wrapper<DiscussshebeizulinEntity> wrapper);
	
	DiscussshebeizulinVO selectVO(@Param("ew") Wrapper<DiscussshebeizulinEntity> wrapper);
	
	List<DiscussshebeizulinView> selectListView(@Param("ew") Wrapper<DiscussshebeizulinEntity> wrapper);

	List<DiscussshebeizulinView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussshebeizulinEntity> wrapper);

	
	DiscussshebeizulinView selectView(@Param("ew") Wrapper<DiscussshebeizulinEntity> wrapper);
	

}
