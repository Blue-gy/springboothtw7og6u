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

import com.entity.ChangdiyuyueEntity;
import com.entity.ChangdixinxiEntity;
import com.entity.view.ChangdiyuyueView;

import com.service.ChangdiyuyueService;
import com.service.ChangdixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import com.utils.TimeSlotUtils;
import java.io.IOException;

/**
 * 场地预约
 * 后端接口
 * @author
 * @email
 * @date 2030-02-26 18:34:49
 */
@RestController
@RequestMapping("/changdiyuyue")
public class ChangdiyuyueController {
    @Autowired
    private ChangdiyuyueService changdiyuyueService;

    @Autowired
    private ChangdixinxiService changdixinxiService;








    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChangdiyuyueEntity changdiyuyue,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			changdiyuyue.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ChangdiyuyueEntity> ew = new EntityWrapper<ChangdiyuyueEntity>();

		PageUtils page = changdiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changdiyuyue), params), params));

        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChangdiyuyueEntity changdiyuyue,
		HttpServletRequest request){
        EntityWrapper<ChangdiyuyueEntity> ew = new EntityWrapper<ChangdiyuyueEntity>();

		PageUtils page = changdiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changdiyuyue), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChangdiyuyueEntity changdiyuyue){
       	EntityWrapper<ChangdiyuyueEntity> ew = new EntityWrapper<ChangdiyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( changdiyuyue, "changdiyuyue"));
        return R.ok().put("data", changdiyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChangdiyuyueEntity changdiyuyue){
        EntityWrapper< ChangdiyuyueEntity> ew = new EntityWrapper< ChangdiyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( changdiyuyue, "changdiyuyue"));
		ChangdiyuyueView changdiyuyueView =  changdiyuyueService.selectView(ew);
		return R.ok("查询场地预约成功").put("data", changdiyuyueView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChangdiyuyueEntity changdiyuyue = changdiyuyueService.selectById(id);
        return R.ok().put("data", changdiyuyue);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChangdiyuyueEntity changdiyuyue = changdiyuyueService.selectById(id);
        return R.ok().put("data", changdiyuyue);
    }

    /**
     * 获取所有时段选项（10小节+5大节）
     */
    @RequestMapping("/timeSlots")
    public R timeSlots(){
        return R.ok().put("data", TimeSlotUtils.getAllTimeSlots());
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChangdiyuyueEntity changdiyuyue, HttpServletRequest request){
    	return doSave(changdiyuyue, request);
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChangdiyuyueEntity changdiyuyue, HttpServletRequest request){
    	return doSave(changdiyuyue, request);
    }

    /**
     * 统一的保存逻辑（个人中心/前端/管理端共用）
     */
    private R doSave(ChangdiyuyueEntity c, HttpServletRequest request){
    	// 基本校验
    	if (c.getChangdimingcheng() == null || c.getShijianduan() == null || c.getYuyueshijian() == null) {
    		return R.error("场地名称、时间段、预约日期不能为空");
    	}
    	if (c.getYuyueleixing() == null || c.getYuyueleixing().isEmpty()) {
    		c.setYuyueleixing("个人");
    	}

    	// 1. 展开大节
    	List<String> periods = TimeSlotUtils.expandToIndividualPeriods(c.getShijianduan());

    	// 2. 逐个小节进行冲突检测和容量检查
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String yuyueriqi = sdf.format(c.getYuyueshijian());

    	for (String period : periods) {
    		// 冲突检测
    		String conflictMsg = checkVenueAvailability(c.getChangdimingcheng(), yuyueriqi, period, c.getYuyueleixing(), c.getId());
    		if (conflictMsg != null) {
    			return R.error(conflictMsg);
    		}

    		// 容量检查（仅个人预约需检查）
    		if ("个人".equals(c.getYuyueleixing())) {
    			String capacityMsg = checkVenueCapacity(c.getChangdimingcheng());
    			if (capacityMsg != null) {
    				return R.error(capacityMsg);
    			}
    		}
    	}

    	// 3. 批量创建记录（每个小节一条独立记录）
    	for (String period : periods) {
    		ChangdiyuyueEntity record = new ChangdiyuyueEntity();
    		record.setChangdimingcheng(c.getChangdimingcheng());
    		record.setChangditupian(c.getChangditupian());
    		record.setChangdijieshao(c.getChangdijieshao());
    		record.setChangdidizhi(c.getChangdidizhi());
    		record.setChangdisheshi(c.getChangdisheshi());
    		record.setKaifangshijian(c.getKaifangshijian());
    		record.setChangdijiage(c.getChangdijiage());
    		record.setShijianduan(period);
    		record.setYuyueshijian(c.getYuyueshijian());
    		record.setYonghuzhanghao(c.getYonghuzhanghao());
    		record.setXingming(c.getXingming());
    		record.setShouji(c.getShouji());
    		record.setSfsh("通过");
    		record.setShhf("");
    		record.setYuyueleixing(c.getYuyueleixing());
    		changdiyuyueService.insert(record);

    		// 个人预约：更新场地已预约人数
    		if ("个人".equals(c.getYuyueleixing())) {
    			updateVenueReservedCount(c.getChangdimingcheng());
    		}
    	}

    	return R.ok();
    }

    /**
     * 冲突检测
     * 冲突规则:
     *   已有集体预约 → 任何新预约都冲突
     *   新预约是集体 + 已有个人预约 → 冲突（集体不可覆盖个人）
     *   新预约是个人 + 已有个人预约 → 不冲突（多人可同时预约）
     *   新预约是个人 + 已有集体预约 → 冲突（集体占用整个时段）
     * @return 冲突信息，无冲突返回null
     */
    private String checkVenueAvailability(String changdimingcheng, String yuyueriqi, String shijianduan, String yuyueleixing, Long excludeId) {
    	SimpleDateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date start, end;
    	try {
    		start = full.parse(yuyueriqi + " 00:00:00");
    		end   = full.parse(yuyueriqi + " 23:59:59");
    	} catch (ParseException e) {
    		return "日期解析失败";
    	}

    	EntityWrapper<ChangdiyuyueEntity> wrapper = new EntityWrapper<>();
    	wrapper.eq("changdimingcheng", changdimingcheng)
    	       .eq("shijianduan", shijianduan)
    	       .in(true, "sfsh", Arrays.asList("通过", "是"))
    	       .ge("yuyueshijian", start)
    	       .le("yuyueshijian", end);
    	if (excludeId != null) {
    		wrapper.ne("id", excludeId);
    	}

    	List<ChangdiyuyueEntity> existing = changdiyuyueService.selectList(wrapper);
    	boolean hasCollective = false;
    	boolean hasIndividual = false;
    	for (ChangdiyuyueEntity e : existing) {
    		if ("集体".equals(e.getYuyueleixing())) {
    			hasCollective = true;
    		} else if ("个人".equals(e.getYuyueleixing())) {
    			hasIndividual = true;
    		}
    	}

    	// 规则1: 已有集体预约 → 任何新预约都冲突
    	if (hasCollective) {
    		return "该场地在此时间段已被集体预约，不可预约";
    	}
    	// 规则2: 新预约是集体 + 已有个人预约 → 冲突
    	if ("集体".equals(yuyueleixing) && hasIndividual) {
    		return "该场地在此时间段已有个人预约，集体预约无法覆盖";
    	}
    	// 规则3: 新预约是个人 + 已有个人预约 → 不冲突（多人可同时预约）
    	// 规则4: 无冲突 → 允许
    	return null;
    }

    /**
     * 容量检查（复用checkCourseCapacity模式）
     * @return 容量不足时返回错误信息，否则返回null
     */
    private String checkVenueCapacity(String changdimingcheng) {
    	EntityWrapper<ChangdixinxiEntity> wrapper = new EntityWrapper<>();
    	wrapper.eq("changdimingcheng", changdimingcheng);
    	ChangdixinxiEntity venue = changdixinxiService.selectOne(wrapper);
    	if (venue == null) {
    		return "场地信息不存在";
    	}

    	Integer limit = venue.getRenshuxianzhi();
    	Integer reserved = venue.getYiyuyuerenshu();
    	if (limit != null && reserved != null && reserved >= limit) {
    		return "该场地预约人数已满，无法预约";
    	}
    	return null;
    }

    /**
     * 更新场地已预约人数（统计所有已通过的个人预约，兼容旧数据sfsh='是'和'通过'）
     */
    private void updateVenueReservedCount(String changdimingcheng) {
    	EntityWrapper<ChangdixinxiEntity> wrapper = new EntityWrapper<>();
    	wrapper.eq("changdimingcheng", changdimingcheng);
    	ChangdixinxiEntity venue = changdixinxiService.selectOne(wrapper);
    	if (venue != null) {
    		// 重新统计该场地的个人预约数（兼容旧数据sfsh='是'和'通过'）
    		EntityWrapper<ChangdiyuyueEntity> countWrapper = new EntityWrapper<>();
    		countWrapper.eq("changdimingcheng", changdimingcheng)
    		            .in(true, "sfsh", Arrays.asList("通过", "是"))
    		            .eq("yuyueleixing", "个人");
    		int count = changdiyuyueService.selectCount(countWrapper);
    		venue.setYiyuyuerenshu(count);
    		changdixinxiService.updateById(venue);
    	}
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChangdiyuyueEntity changdiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(changdiyuyue);
        changdiyuyueService.updateById(changdiyuyue);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ChangdiyuyueEntity> list = new ArrayList<ChangdiyuyueEntity>();
        for(Long id : ids) {
            ChangdiyuyueEntity changdiyuyue = changdiyuyueService.selectById(id);
            changdiyuyue.setSfsh(sfsh);
            changdiyuyue.setShhf(shhf);
            list.add(changdiyuyue);
        }
        changdiyuyueService.updateBatchById(list);
        // 审核后更新预约人数统计
        for (ChangdiyuyueEntity item : list) {
        	if (item.getChangdimingcheng() != null) {
        		updateVenueReservedCount(item.getChangdimingcheng());
        	}
        }
        return R.ok();
    }





    /**
     * 删除（回退余位计数）
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
    	// 先记录需要回退计数的场地名称
    	java.util.Set<String> venueNames = new java.util.HashSet<>();
    	for (Long id : ids) {
    		ChangdiyuyueEntity entity = changdiyuyueService.selectById(id);
    		if (entity != null && "个人".equals(entity.getYuyueleixing())
    				&& ("通过".equals(entity.getSfsh()) || "是".equals(entity.getSfsh()))) {
    			venueNames.add(entity.getChangdimingcheng());
    		}
    	}
        changdiyuyueService.deleteBatchIds(Arrays.asList(ids));
        // 回退计数
        for (String name : venueNames) {
        	updateVenueReservedCount(name);
        }
        return R.ok();
    }










}
