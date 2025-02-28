package com.taotao.cloud.office.myexecl.controller;

import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.core.strategy.WidthStrategy;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import com.taotao.cloud.office.myexecl.pojo.ArtCrowd;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultExcelBuilderExampleController {

    @GetMapping("/default/excel/example")
    public void defaultBuild(HttpServletResponse response) throws Exception {
        List<ArtCrowd> dataList = this.getDataList();
        Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class).build(dataList);
        AttachmentExportUtil.export(workbook, "艺术生信息", response);
    }

    @GetMapping("/default/noStyle/example")
    public void defaultBuildWithNoStyle(HttpServletResponse response) throws Exception {
        List<ArtCrowd> dataList = this.getDataList();
        Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class).noStyle().build(dataList);
        AttachmentExportUtil.export(workbook, "艺术生信息", response);
    }

    @GetMapping("/default/autoWidth/example")
    public void defaultBuildWithAutoWidth(HttpServletResponse response) throws Exception {
        List<ArtCrowd> dataList = this.getDataList();
        Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class).widthStrategy(WidthStrategy.AUTO_WIDTH).build(dataList);
        AttachmentExportUtil.export(workbook, "艺术生信息", response);
    }

    @GetMapping("/default/continue/example")
    public void defaultBuildWithWorkbook(HttpServletResponse response) throws Exception {
        List<ArtCrowd> dataList = this.getDataList();
        Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class).widthStrategy(WidthStrategy.AUTO_WIDTH).build(dataList);

        dataList = this.getDataList();
        workbook = DefaultExcelBuilder.of(ArtCrowd.class, workbook).sheetName("sheet2").widthStrategy(WidthStrategy.NO_AUTO).build(dataList);
        AttachmentExportUtil.export(workbook, "艺术生信息", response);
    }

    private List<ArtCrowd> getDataList() {
        List<ArtCrowd> dataList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            ArtCrowd artCrowd = new ArtCrowd();
            if (i % 2 == 0) {
                artCrowd.setName("Tom");
                artCrowd.setAge(19);
                artCrowd.setGender("Man");
                artCrowd.setPaintingLevel("一级证书");
                artCrowd.setDance(false);
                artCrowd.setAssessmentTime(LocalDateTime.now());
                artCrowd.setHobby(null);
            } else {
                artCrowd.setName("Marry");
                artCrowd.setAge(18);
                artCrowd.setGender("Woman");
                artCrowd.setPaintingLevel("一级证书");
                artCrowd.setDance(true);
                artCrowd.setAssessmentTime(LocalDateTime.now());
                artCrowd.setHobby("钓鱼");
            }
            dataList.add(artCrowd);
        }
        return dataList;
    }


}
