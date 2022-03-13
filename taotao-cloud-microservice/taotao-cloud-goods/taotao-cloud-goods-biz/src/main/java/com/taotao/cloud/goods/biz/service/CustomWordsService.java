package com.taotao.cloud.goods.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.goods.api.vo.CustomWordsVO;
import com.taotao.cloud.goods.biz.entity.CustomWords;

/**
 * 自定义分词业务层
 **/
public interface CustomWordsService extends IService<CustomWords> {

	/**
	 * 自定义分词部署替换
	 *
	 * @return 替换的内容
	 */
	String deploy();

	/**
	 * 是否存在分词
	 *
	 * @param words 分词
	 * @return 是否存在
	 */
	boolean existWords(String words);

	/**
	 * 添加自定义分词
	 *
	 * @param customWordsVO 自定义分词信息
	 * @return 是否添加成功
	 */
	boolean addCustomWords(CustomWordsVO customWordsVO);


	/**
	 * 修改自定义分词
	 *
	 * @param customWordsVO 自定义分词信息
	 * @return 是否修改成功
	 */
	boolean updateCustomWords(CustomWordsVO customWordsVO);

	/**
	 * 删除自定义分词
	 *
	 * @param id 自定义分词id
	 * @return 是否删除成功
	 */
	boolean deleteCustomWords(String id);

	/**
	 * 分页查询自定义分词
	 *
	 * @param words  分词
	 * @param pageVo 分页信息
	 * @return 自定义分词分页信息
	 */
	IPage<CustomWords> getCustomWordsByPage(String words, PageVO pageVo);

}
