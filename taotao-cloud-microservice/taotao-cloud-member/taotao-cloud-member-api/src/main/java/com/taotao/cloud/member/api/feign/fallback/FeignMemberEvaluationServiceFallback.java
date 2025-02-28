package com.taotao.cloud.member.api.feign.fallback;

import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.member.api.feign.IFeignMemberEvaluationService;
import com.taotao.cloud.member.api.model.dto.MemberEvaluationDTO;
import com.taotao.cloud.member.api.model.query.EvaluationPageQuery;
import com.taotao.cloud.member.api.model.vo.MemberEvaluationListVO;
import com.taotao.cloud.member.api.model.vo.MemberEvaluationVO;
import com.taotao.cloud.member.api.model.vo.StoreRatingVO;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;
import java.util.Map;

/**
 * RemoteMemberFallbackImpl
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/11/20 下午4:10
 */
public class FeignMemberEvaluationServiceFallback implements FallbackFactory<IFeignMemberEvaluationService> {
	@Override
	public IFeignMemberEvaluationService create(Throwable throwable) {
		return new IFeignMemberEvaluationService() {

			@Override
			public Result<Long> count(Long goodsId, String name) {
				return null;
			}

			@Override
			public Result<Long> getEvaluationCount(EvaluationPageQuery queryParams) {
				return null;
			}

			@Override
			public Result<List<Map<String, Object>>> memberEvaluationNum() {
				return null;
			}

			@Override
			public Result<Boolean> addMemberEvaluation(MemberEvaluationDTO memberEvaluationDTO, boolean b) {
				return null;
			}

			@Override
			public StoreRatingVO getStoreRatingVO(Long id, String name) {
				return null;
			}

			@Override
			public MemberEvaluationVO queryById(Long id) {
				return null;
			}

			@Override
			public void reply(Long id, String reply, String replyImage) {

			}

			@Override
			public PageResult<MemberEvaluationListVO> queryPage(
				EvaluationPageQuery evaluationPageQuery) {
				return null;
			}
		};
	}
}
