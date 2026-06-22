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

import com.entity.ChangdixinxiEntity;
import com.entity.ChangdiyuyueEntity;
import com.entity.view.ChangdixinxiView;

import com.service.ChangdixinxiService;
import com.service.ChangdiyuyueService;
import com.service.TokenService;
import com.utils.TimeSlotUtils;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 场地信息
 * 后端接口
 * @author
 * @email
 * @date 2030-02-26 18:34:49
 */
@RestController
@RequestMapping("/changdixinxi")
public class ChangdixinxiController {
    @Autowired
    private ChangdixinxiService changdixinxiService;

    @Autowired
    private ChangdiyuyueService changdiyuyueService;

    @Autowired
    private StoreupService storeupService;








    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChangdixinxiEntity changdixinxi,
		HttpServletRequest request){
        EntityWrapper<ChangdixinxiEntity> ew = new EntityWrapper<ChangdixinxiEntity>();

		PageUtils page = changdixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changdixinxi), params), params));

        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChangdixinxiEntity changdixinxi,
		HttpServletRequest request){
        EntityWrapper<ChangdixinxiEntity> ew = new EntityWrapper<ChangdixinxiEntity>();

		PageUtils page = changdixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changdixinxi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChangdixinxiEntity changdixinxi){
       	EntityWrapper<ChangdixinxiEntity> ew = new EntityWrapper<ChangdixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( changdixinxi, "changdixinxi"));
        return R.ok().put("data", changdixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChangdixinxiEntity changdixinxi){
        EntityWrapper< ChangdixinxiEntity> ew = new EntityWrapper< ChangdixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( changdixinxi, "changdixinxi"));
		ChangdixinxiView changdixinxiView =  changdixinxiService.selectView(ew);
		return R.ok("查询场地信息成功").put("data", changdixinxiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChangdixinxiEntity changdixinxi = changdixinxiService.selectById(id);
        return R.ok().put("data", changdixinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChangdixinxiEntity changdixinxi = changdixinxiService.selectById(id);
        return R.ok().put("data", changdixinxi);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChangdixinxiEntity changdixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changdixinxi);
        changdixinxiService.insert(changdixinxi);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChangdixinxiEntity changdixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changdixinxi);
        changdixinxiService.insert(changdixinxi);
        return R.ok();
    }

    /**
     * 查询有空位的场地
     * 请求: {yuyueriqi: "yyyy-MM-dd", shijianduan: "第一大节(08:00-09:40)", yuyueleixing: "个人"}
     * 冲突规则: 集体预约互斥所有, 个人预约不互斥但集体不能覆盖已有个人
     * 逻辑: 展开时段→遍历场地→检查冲突和容量→返回可用列表
     */
    @IgnoreAuth
    @RequestMapping("/available")
    public R available(@RequestBody Map<String, String> params){
    	String yuyueriqi = params.get("yuyueriqi");
    	String shijianduan = params.get("shijianduan");
    	String yuyueleixing = params.get("yuyueleixing");
    	if (yuyueleixing == null || yuyueleixing.isEmpty()) {
    		yuyueleixing = "个人"; // 默认按个人预约规则筛选
    	}

    	if (yuyueriqi == null || shijianduan == null) {
    		return R.error("预约日期和时间段不能为空");
    	}

    	// 展开大节为多个小节
    	List<String> periods = TimeSlotUtils.expandToIndividualPeriods(shijianduan);

    	// 获取所有场地（按id倒序，与list接口排序一致）
    	EntityWrapper<ChangdixinxiEntity> venueWrapper = new EntityWrapper<>();
    	venueWrapper.orderBy("id", false);
    	List<ChangdixinxiEntity> allVenues = changdixinxiService.selectList(venueWrapper);

    	List<ChangdixinxiEntity> availableVenues = new ArrayList<>();

    	SimpleDateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date dayStart, dayEnd;
    	try {
    		dayStart = full.parse(yuyueriqi + " 00:00:00");
    		dayEnd   = full.parse(yuyueriqi + " 23:59:59");
    	} catch (ParseException e) {
    		return R.error("日期格式错误");
    	}

    	for (ChangdixinxiEntity venue : allVenues) {
    		boolean isAvailable = true;

    		for (String period : periods) {
    			// 查询该场地在该日期+时段的所有已通过预约（兼容旧数据sfsh='是'和'通过'）
    			EntityWrapper<ChangdiyuyueEntity> yuyueWrapper = new EntityWrapper<>();
    			yuyueWrapper.eq("changdimingcheng", venue.getChangdimingcheng())
    			            .eq("shijianduan", period)
    			            .in(true, "sfsh", Arrays.asList("通过", "是"))
    			            .ge("yuyueshijian", dayStart)
    			            .le("yuyueshijian", dayEnd);
    			List<ChangdiyuyueEntity> existingList = changdiyuyueService.selectList(yuyueWrapper);

    			for (ChangdiyuyueEntity existing : existingList) {
    				String existingType = existing.getYuyueleixing();
    				// 规则1: 已有集体预约 → 任何新预约都冲突
    				if ("集体".equals(existingType)) {
    					isAvailable = false;
    					break;
    				}
    				// 规则2: 新预约是集体, 已有个人预约 → 冲突（集体不能覆盖个人）
    				if ("集体".equals(yuyueleixing) && "个人".equals(existingType)) {
    					isAvailable = false;
    					break;
    				}
    				// 规则3: 新预约是个人, 已有个人预约 → 不冲突（多人可同时预约）
    			}
    			if (!isAvailable) break;

    			// 容量检查（仅个人预约受容量限制）
    			if ("个人".equals(yuyueleixing)) {
    				Integer limit = venue.getRenshuxianzhi();
    				Integer reserved = venue.getYiyuyuerenshu();
    				if (limit != null && reserved != null && reserved >= limit) {
    					isAvailable = false;
    					break;
    				}
    			}
    		}

    		if (isAvailable) {
    			availableVenues.add(venue);
    		}
    	}

    	return R.ok().put("data", availableVenues);
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChangdixinxiEntity changdixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(changdixinxi);
        changdixinxiService.updateById(changdixinxi);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        changdixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }









}
