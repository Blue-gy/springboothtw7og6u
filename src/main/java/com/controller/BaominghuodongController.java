package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.BaominghuodongEntity;
import com.entity.view.BaominghuodongView;

import com.service.BaominghuodongService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 报名活动
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
@RestController
@RequestMapping("/baominghuodong")
public class BaominghuodongController {
    @Autowired
    private BaominghuodongService baominghuodongService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaominghuodongEntity baominghuodong,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			baominghuodong.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<BaominghuodongEntity> ew = new EntityWrapper<BaominghuodongEntity>();

		PageUtils page = baominghuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baominghuodong), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BaominghuodongEntity baominghuodong, 
		HttpServletRequest request){
        EntityWrapper<BaominghuodongEntity> ew = new EntityWrapper<BaominghuodongEntity>();

		PageUtils page = baominghuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baominghuodong), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaominghuodongEntity baominghuodong){
       	EntityWrapper<BaominghuodongEntity> ew = new EntityWrapper<BaominghuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baominghuodong, "baominghuodong")); 
        return R.ok().put("data", baominghuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaominghuodongEntity baominghuodong){
        EntityWrapper< BaominghuodongEntity> ew = new EntityWrapper< BaominghuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baominghuodong, "baominghuodong")); 
		BaominghuodongView baominghuodongView =  baominghuodongService.selectView(ew);
		return R.ok("查询报名活动成功").put("data", baominghuodongView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaominghuodongEntity baominghuodong = baominghuodongService.selectById(id);
        return R.ok().put("data", baominghuodong);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaominghuodongEntity baominghuodong = baominghuodongService.selectById(id);
        return R.ok().put("data", baominghuodong);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BaominghuodongEntity baominghuodong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baominghuodong);
        baominghuodongService.insert(baominghuodong);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaominghuodongEntity baominghuodong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(baominghuodong);
        baominghuodongService.insert(baominghuodong);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaominghuodongEntity baominghuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baominghuodong);
        baominghuodongService.updateById(baominghuodong);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<BaominghuodongEntity> list = new ArrayList<BaominghuodongEntity>();
        for(Long id : ids) {
            BaominghuodongEntity baominghuodong = baominghuodongService.selectById(id);
            baominghuodong.setSfsh(sfsh);
            baominghuodong.setShhf(shhf);
            list.add(baominghuodong);
        }
        baominghuodongService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        baominghuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
