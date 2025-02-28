package com.taotao.cloud.distribution.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.taotao.cloud.distribution.api.web.query.DistributionGoodsPageQuery;
import com.taotao.cloud.distribution.api.web.vo.DistributionGoodsVO;
import com.taotao.cloud.distribution.biz.model.entity.DistributionGoods;
import java.util.List;


/**
 * 分销商品业务层
 */
public interface DistributionGoodsService extends IService<DistributionGoods> {

    /**
     * 根据条件分页查询分销商品信息
     *
     * @param distributionGoodsPageQuery 商品条件
     * @return 分页分销商品信息
     */
    IPage<DistributionGoodsVO> goodsPage(DistributionGoodsPageQuery distributionGoodsPageQuery);


    /**
     * 根据条件查询分销商品信息列表
     *
     * @param distributionGoodsPageQuery 条件
     * @return 分销商品信息列表
     */
    List<DistributionGoods> getDistributionGoodsList(DistributionGoodsPageQuery distributionGoodsPageQuery);

    /**
     * 根据条件查询分销商品信息
     *
     * @param distributionGoodsPageQuery 条件
     * @return 分销商品信息
     */
    DistributionGoods getDistributionGoods(DistributionGoodsPageQuery distributionGoodsPageQuery);

    /**
     * 根据条件删除分销商品
     *
     * @param distributionGoodsPageQuery 条件
     */
    boolean deleteDistributionGoods(DistributionGoodsPageQuery distributionGoodsPageQuery);

    /**
     * 获取分销商品
     *
     * @param id 分销商品ID
     * @return 分销商品
     */
    DistributionGoods distributionGoodsVO(String id);

    /**
     * 获取分销商品
     *
     * @param skuId SKUId
     * @return 分销商品
     */
    DistributionGoods distributionGoodsVOBySkuId(String skuId);

    /**
     * 批量获取分销商品
     *
     * @param skuIds sku id集合
     * @return 分销商品
     */
    List<DistributionGoods> distributionGoods(List<String> skuIds);

    /**
     * 选择分销商品
     *
     * @param skuId      SKU ID
     * @param commission 佣金
     * @param storeId 店铺id
     * @return
     */
    DistributionGoods checked(String skuId, BigDecimal commission, String storeId);

}
