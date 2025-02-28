package com.taotao.cloud.operation.biz.api.controller.manger;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.logger.annotation.RequestLogger;
import com.taotao.cloud.operation.api.web.query.ArticlePageQuery;
import com.taotao.cloud.operation.api.enums.ArticleEnum;
import com.taotao.cloud.operation.api.web.vo.ArticleVO;
import com.taotao.cloud.operation.biz.model.entity.Article;
import com.taotao.cloud.operation.biz.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,文章接口
 */
@RestController
@Tag(name = "管理端,文章接口")
@RequestMapping("/manager/other/article")
public class ArticleManagerController {

	/**
	 * 文章
	 */
	@Autowired
	private ArticleService articleService;
	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "查看文章")
	@GetMapping(value = "/{id}")
	public Result<Article> get(@Parameter(description = "文章ID") @PathVariable String id) {
		return Result.success(articleService.getById(id));
	}
	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "分页获取")
	@GetMapping(value = "/getByPage")
	public Result<IPage<ArticleVO>> getByPage(ArticlePageQuery articlePageQuery) {
		return Result.success(articleService.managerArticlePage(articlePageQuery));
	}
	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "添加文章")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Result<Article> save(@RequestBody Article article) {
		article.setType(ArticleEnum.OTHER.name());
		articleService.save(article);
		return Result.success(article);
	}
	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "修改文章")
	@PutMapping(value = "update/{id}", consumes = "application/json", produces = "application/json")
	public Result<Article> update(@RequestBody Article article,
		@Parameter(description = "文章ID") @PathVariable("id") String id) {
		article.setId(id);
		return Result.success(articleService.updateArticle(article));
	}
	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "修改文章状态")
	@PutMapping("update/status/{id}")
	public Result<Article> updateStatus(
		@Parameter(description = "文章ID") @PathVariable("id") String id,
		@Parameter(description = "操作状态") boolean status) {
		articleService.updateArticleStatus(id, status);
		return Result.success();
	}

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@Operation(summary = "批量删除")
	@DeleteMapping(value = "/delByIds/{id}")
	public Result<Object> delAllByIds(@Parameter(description = "文章ID") @PathVariable String id) {
		articleService.customRemove(id);
		return Result.success();
	}


}
