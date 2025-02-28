package com.taotao.cloud.sensitive.sensitive.sensitive.annotation.custom;



import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.SensitiveEntry;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果对象中属性为另外一个对象(集合)，则可以使用这个注解指定。(自定义)
 * <p>
 * 1. 如果属性为 Iterable 的子类集合，则当做列表处理，遍历其中的对象
 * 2. 如果是普通对象，则处理对象中的脱敏信息
 * 3. 如果是普通字段/MAP，则不做处理
 *
 */
@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveEntry
public @interface SensitiveEntryCustom {
}
