package com.school.master.common.utils;
//
//import cn.hutool.core.io.IoUtil;
//import cn.hutool.poi.excel.ExcelUtil;
//import cn.hutool.poi.excel.ExcelWriter;
//import com.school.master.common.annotation.ExcelColumn;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.AnnotatedType;
//import java.lang.reflect.Field;
//import java.net.URLDecoder;
//import java.util.List;
//
//@Component
public class ExcelExportUtil {
//    private final static String EXCEL2003 = ".xls";
//    private final static String EXCEL2007 = ".xlsx";
//
//    public static <T> void export(HttpServletResponse response, String excelName, List<T> dates, boolean isXlsx) {
//        if (dates != null) {
//            Class<?> aClass = dates.get(1).getClass();
//            ExcelWriter writer = export(dates, null, aClass, isXlsx);
//        }
//
//    }
//
//    public static <T> ExcelWriter export(HttpServletResponse response, List datas, String sheet, Class<T> tClass, boolean isXlsx) {
//        String tableName = "";
//        if (isXlsx) {
//            tableName += EXCEL2007;
//        }
//        tableName += EXCEL2003;
//        ExcelWriter writer = ExcelUtil.getWriter(isXlsx);
//        setTableColumn(writer, tClass);
//        writer.write(datas, true);
//        return writer;
//    }
//
//    public static <T> void writer(HttpServletResponse response, ExcelWriter writer) {
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
//        String name = URLDecoder.decode(writer.merge(), "UTF-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + name);
//        ServletOutputStream out = null;
//        try {
//            out = response.getOutputStream();
//            writer.flush(out, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭writer，释放内存
//            writer.close();
//        }
//        //此处记得关闭输出Servlet流
//        IoUtil.close(out);
//
//    }
//
//    private static <T> void setTableColumn(ExcelWriter writer, Class<?> tClass) {
//        Field[] fields = tClass.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            //获取注解
//            AnnotatedType annotatedType = field.getAnnotatedType();
//            ExcelColumn declaredAnnotation = annotatedType.getDeclaredAnnotation(ExcelColumn.class);
//            if (declaredAnnotation != null && declaredAnnotation.rank() > 0) {
//
//            }
//            String column = declaredAnnotation.column();
//            int rank = declaredAnnotation.rank();
//            writer.addHeaderAlias(field.getName(), column);
//        }
//    }
//
//
}
