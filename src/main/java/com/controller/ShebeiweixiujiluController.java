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

import com.entity.ShebeiweixiujiluEntity;
import com.entity.view.ShebeiweixiujiluView;

import com.service.ShebeiweixiujiluService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 设备维修记录
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
@RestController
@RequestMapping("/shebeiweixiujilu")
public class ShebeiweixiujiluController {
    @Autowired
    private ShebeiweixiujiluService shebeiweixiujiluService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShebeiweixiujiluEntity shebeiweixiujilu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yuangong")) {
			shebeiweixiujilu.setYuangongzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ShebeiweixiujiluEntity> ew = new EntityWrapper<ShebeiweixiujiluEntity>();

		PageUtils page = shebeiweixiujiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shebeiweixiujilu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShebeiweixiujiluEntity shebeiweixiujilu, 
		HttpServletRequest request){
        EntityWrapper<ShebeiweixiujiluEntity> ew = new EntityWrapper<ShebeiweixiujiluEntity>();

		PageUtils page = shebeiweixiujiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shebeiweixiujilu), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShebeiweixiujiluEntity shebeiweixiujilu){
       	EntityWrapper<ShebeiweixiujiluEntity> ew = new EntityWrapper<ShebeiweixiujiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shebeiweixiujilu, "shebeiweixiujilu")); 
        return R.ok().put("data", shebeiweixiujiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShebeiweixiujiluEntity shebeiweixiujilu){
        EntityWrapper< ShebeiweixiujiluEntity> ew = new EntityWrapper< ShebeiweixiujiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shebeiweixiujilu, "shebeiweixiujilu")); 
		ShebeiweixiujiluView shebeiweixiujiluView =  shebeiweixiujiluService.selectView(ew);
		return R.ok("查询设备维修记录成功").put("data", shebeiweixiujiluView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShebeiweixiujiluEntity shebeiweixiujilu = shebeiweixiujiluService.selectById(id);
        return R.ok().put("data", shebeiweixiujilu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShebeiweixiujiluEntity shebeiweixiujilu = shebeiweixiujiluService.selectById(id);
        return R.ok().put("data", shebeiweixiujilu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShebeiweixiujiluEntity shebeiweixiujilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shebeiweixiujilu);
        shebeiweixiujiluService.insert(shebeiweixiujilu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShebeiweixiujiluEntity shebeiweixiujilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shebeiweixiujilu);
        shebeiweixiujiluService.insert(shebeiweixiujilu);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShebeiweixiujiluEntity shebeiweixiujilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shebeiweixiujilu);
        shebeiweixiujiluService.updateById(shebeiweixiujilu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shebeiweixiujiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
