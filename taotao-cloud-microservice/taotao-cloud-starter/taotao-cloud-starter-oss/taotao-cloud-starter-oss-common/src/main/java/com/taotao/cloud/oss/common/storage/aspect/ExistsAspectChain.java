package com.taotao.cloud.oss.common.storage.aspect;

import com.taotao.cloud.oss.common.storage.FileInfo;
import com.taotao.cloud.oss.common.storage.platform.FileStorage;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

/**
 * 文件是否存在的切面调用链
 */
@Getter
@Setter
public class ExistsAspectChain {

    private ExistsAspectChainCallback callback;
    private Iterator<FileStorageAspect> aspectIterator;

    public ExistsAspectChain(Iterable<FileStorageAspect> aspects,ExistsAspectChainCallback callback) {
        this.aspectIterator = aspects.iterator();
        this.callback = callback;
    }

    /**
     * 调用下一个切面
     */
    public boolean next(FileInfo fileInfo, FileStorage fileStorage) {
        if (aspectIterator.hasNext()) {//还有下一个
            return aspectIterator.next().existsAround(this,fileInfo,fileStorage);
        } else {
            return callback.run(fileInfo,fileStorage);
        }
    }
}
