package com.taotao.cloud.promotion.biz.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.promotion.biz.model.entity.PointsGoodsCategory;
import com.taotao.cloud.promotion.biz.mapper.PointsGoodsCategoryMapper;
import com.taotao.cloud.promotion.biz.service.PointsGoodsCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 积分商品分类业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 16:46:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PointsGoodsCategoryServiceImpl extends ServiceImpl<PointsGoodsCategoryMapper, PointsGoodsCategory> implements
	PointsGoodsCategoryService {

    /**
     * 添加积分商品分类
     *
     * @param pointsGoodsCategory 积分商品分类信息
     * @return 是否添加成功
     */
    @Override
    public boolean addCategory(PointsGoodsCategory pointsGoodsCategory) {
        this.checkNameDuplicate(pointsGoodsCategory.getName(), null);
        return this.save(pointsGoodsCategory);
    }

    /**
     * 更新积分商品分类
     *
     * @param pointsGoodsCategory 积分商品分类信息
     * @return 是否更新成功
     */
    @Override
    public boolean updateCategory(PointsGoodsCategory pointsGoodsCategory) {
        this.checkExist(pointsGoodsCategory.getId());
        this.checkNameDuplicate(pointsGoodsCategory.getName(), pointsGoodsCategory.getId());
        return this.updateById(pointsGoodsCategory);
    }

    /**
     * 删除积分商品类型
     *
     * @param id 积分商品分类id
     * @return 是否删除成功
     */
    @Override
    public boolean deleteCategory(String id) {
        return this.removeById(id);
    }

    /**
     * 分页获取积分商品类型
     *
     * @param name 类型名称
     * @param page 分页参数
     * @return 积分商品类型分页数据
     */
    @Override
    public IPage<PointsGoodsCategory> getCategoryByPage(String name, PageVO page) {
        LambdaQueryWrapper<PointsGoodsCategory> queryWrapper = new LambdaQueryWrapper<>();
        if (CharSequenceUtil.isNotEmpty(name)) {
            queryWrapper.like(PointsGoodsCategory::getName, name);
        }
        page.setOrder("ASC");
        page.setSort("sort_order");
        return this.page(PageUtil.initPage(page), queryWrapper);
    }

    /**
     * 获取积分商品类型详情
     *
     * @param id 积分商品类型id
     * @return 积分商品类型详情
     */
    @Override
    public PointsGoodsCategory getCategoryDetail(String id) {
        return this.checkExist(id);
    }

    private void checkNameDuplicate(String name, String id) {
        LambdaQueryWrapper<PointsGoodsCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PointsGoodsCategory::getName, name);
        if (id != null) {
            queryWrapper.ne(PointsGoodsCategory::getId, id);
        }
        if (this.getOne(queryWrapper) != null) {
            log.error("当前积分商品分类名称" + name + "已存在！");
            throw new BusinessException();
        }
    }

    /**
     * 根据ID检查积分商品分类是否存在，如存在则范围积分商品分类
     *
     * @param id 积分商品分类ID
     * @return 积分商品分类
     */
    private PointsGoodsCategory checkExist(String id) {
        PointsGoodsCategory category = this.getById(id);
        if (category == null) {
            log.error("积分商品分类id为" + id + "的分类不存在");
            throw new BusinessException();
        }
        return category;
    }

}
