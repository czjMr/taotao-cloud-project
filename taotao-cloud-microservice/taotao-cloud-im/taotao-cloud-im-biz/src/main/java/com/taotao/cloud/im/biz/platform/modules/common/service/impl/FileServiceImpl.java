package com.taotao.cloud.im.biz.platform.modules.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import com.platform.common.constant.ApiConstant;
import com.platform.common.exception.BaseException;
import com.platform.common.upload.config.UploadFileConfig;
import com.platform.common.upload.utils.BaseFileUtils;
import com.platform.common.upload.utils.OssUtils;
import com.platform.common.upload.vo.UploadAudioVo;
import com.platform.common.upload.vo.UploadFileVo;
import com.platform.common.upload.vo.UploadVideoVo;
import com.platform.modules.chat.config.TencentConfig;
import com.platform.modules.chat.utils.TencentUtils;
import com.platform.modules.common.config.OssConfig;
import com.platform.modules.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private TencentConfig tencentConfig;

    @Override
    public UploadFileVo uploadFile(MultipartFile file) {
        String fileType = FileNameUtil.extName(file.getOriginalFilename());
        if ("webp".equalsIgnoreCase(fileType)) {
            throw new BaseException(StrUtil.format("暂不支持{}格式上传", fileType));
        }
        // 初始化
        BaseFileUtils.init(BeanUtil.toBean(ossConfig, UploadFileConfig.class));
        // 上传
        return OssUtils.uploadFile(file);
    }

    @Override
    public UploadVideoVo uploadVideo(MultipartFile videoFile) {
        // 初始化
        BaseFileUtils.init(BeanUtil.toBean(ossConfig, UploadFileConfig.class));
        // 上传视频文件
        UploadFileVo videoFileVo = OssUtils.uploadFile(videoFile);
        return BeanUtil.toBean(videoFileVo, UploadVideoVo.class)
                .setScreenShot(videoFileVo.getFullPath() + ApiConstant.VIDEO_PARAM);
    }

    @Override
    public UploadAudioVo uploadAudio(MultipartFile audioFile) {
        // 初始化
        BaseFileUtils.init(BeanUtil.toBean(ossConfig, UploadFileConfig.class));
        // 上传音频文件
        UploadFileVo audioFileVo = OssUtils.uploadFile(audioFile);
        String data;
        try {
            data = Base64.encode(audioFile.getInputStream());
        } catch (IOException e) {
            throw new BaseException("语音识别接口调用异常，请稍后再试");
        }
        return BeanUtil.toBean(audioFileVo, UploadAudioVo.class).setSourceText(TencentUtils.audio2Text(tencentConfig, data));
    }

}
