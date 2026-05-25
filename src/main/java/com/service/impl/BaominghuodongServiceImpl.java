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


import com.dao.BaominghuodongDao;
import com.entity.BaominghuodongEntity;
import com.service.BaominghuodongService;
import com.entity.vo.BaominghuodongVO;
import com.entity.view.BaominghuodongView;

@Service("baominghuodongService")
public class BaominghuodongServiceImpl extends ServiceImpl<BaominghuodongDao, BaominghuodongEntity> implements BaominghuodongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaominghuodongEntity> page = this.selectPage(
                new Query<BaominghuodongEntity>(params).getPage(),
                new EntityWrapper<BaominghuodongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BaominghuodongEntity> wrapper) {
		  Page<BaominghuodongView> page =new Query<BaominghuodongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BaominghuodongVO> selectListVO(Wrapper<BaominghuodongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BaominghuodongVO selectVO(Wrapper<BaominghuodongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BaominghuodongView> selectListView(Wrapper<BaominghuodongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BaominghuodongView selectView(Wrapper<BaominghuodongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
