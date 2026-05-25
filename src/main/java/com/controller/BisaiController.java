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

import com.entity.BisaiEntity;
import com.entity.view.BisaiView;

import com.service.BisaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 比赛
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
@RestController
@RequestMapping("/bisai")
public class BisaiController {
    @Autowired
    private BisaiService bisaiService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BisaiEntity bisai,
		HttpServletRequest request){
        EntityWrapper<BisaiEntity> ew = new EntityWrapper<BisaiEntity>();

		PageUtils page = bisaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisai), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BisaiEntity bisai, 
		HttpServletRequest request){
        EntityWrapper<BisaiEntity> ew = new EntityWrapper<BisaiEntity>();

		PageUtils page = bisaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisai), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BisaiEntity bisai){
       	EntityWrapper<BisaiEntity> ew = new EntityWrapper<BisaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bisai, "bisai")); 
        return R.ok().put("data", bisaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BisaiEntity bisai){
        EntityWrapper< BisaiEntity> ew = new EntityWrapper< BisaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bisai, "bisai")); 
		BisaiView bisaiView =  bisaiService.selectView(ew);
		return R.ok("查询比赛成功").put("data", bisaiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BisaiEntity bisai = bisaiService.selectById(id);
        return R.ok().put("data", bisai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BisaiEntity bisai = bisaiService.selectById(id);
        return R.ok().put("data", bisai);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BisaiEntity bisai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(bisai);
        bisaiService.insert(bisai);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BisaiEntity bisai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(bisai);
        bisaiService.insert(bisai);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BisaiEntity bisai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bisai);
        bisaiService.updateById(bisai);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bisaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
