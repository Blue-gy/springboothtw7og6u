package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussshebeizulinDao;
import com.entity.DiscussshebeizulinEntity;
import com.service.DiscussshebeizulinService;
import com.entity.vo.DiscussshebeizulinVO;
import com.entity.view.DiscussshebeizulinView;

@Service("discussshebeizulinService")
public class DiscussshebeizulinServiceImpl extends ServiceImpl<DiscussshebeizulinDao, DiscussshebeizulinEntity> implements DiscussshebeizulinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussshebeizulinEntity> page = this.selectPage(
                new Query<DiscussshebeizulinEntity>(params).getPage(),
                new EntityWrapper<DiscussshebeizulinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussshebeizulinEntity> wrapper) {
		  Page<DiscussshebeizulinView> page =new Query<DiscussshebeizulinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussshebeizulinVO> selectListVO(Wrapper<DiscussshebeizulinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussshebeizulinVO selectVO(Wrapper<DiscussshebeizulinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussshebeizulinView> selectListView(Wrapper<DiscussshebeizulinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussshebeizulinView selectView(Wrapper<DiscussshebeizulinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
