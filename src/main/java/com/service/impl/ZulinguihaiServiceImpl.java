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


import com.dao.ZulinguihaiDao;
import com.entity.ZulinguihaiEntity;
import com.service.ZulinguihaiService;
import com.entity.vo.ZulinguihaiVO;
import com.entity.view.ZulinguihaiView;

@Service("zulinguihaiService")
public class ZulinguihaiServiceImpl extends ServiceImpl<ZulinguihaiDao, ZulinguihaiEntity> implements ZulinguihaiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZulinguihaiEntity> page = this.selectPage(
                new Query<ZulinguihaiEntity>(params).getPage(),
                new EntityWrapper<ZulinguihaiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZulinguihaiEntity> wrapper) {
		  Page<ZulinguihaiView> page =new Query<ZulinguihaiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZulinguihaiVO> selectListVO(Wrapper<ZulinguihaiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZulinguihaiVO selectVO(Wrapper<ZulinguihaiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZulinguihaiView> selectListView(Wrapper<ZulinguihaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZulinguihaiView selectView(Wrapper<ZulinguihaiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
