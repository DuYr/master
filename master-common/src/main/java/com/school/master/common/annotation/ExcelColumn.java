package com.school.master.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义实体类所需要的bean(Excel属性标题、位置等)
 * 
 * @author Lynch
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel标题
     * 
     * @return
     * @author Lynch
     */
    String column() default "";

    /**
     * Excel从左往右排列位置
     * 
     * @return
     * @author Lynch
     */
    int rank() default 0;
}