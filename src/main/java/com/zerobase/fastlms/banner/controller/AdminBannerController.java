package com.zerobase.fastlms.banner.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {
        parameter.init();

        List<BannerDto> bannerList =  bannerService.list(parameter);

        long totalCount = 0;

        if (!CollectionUtils.isEmpty(bannerList)) {
            totalCount = bannerList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();

        String pagerHtml = super.getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", bannerList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "/admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(Model model, HttpServletRequest request,
                      BannerInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");

        BannerDto detail = new BannerDto();

        if (editMode) {
            long id = parameter.getId();

            BannerDto existBanner = bannerService.getById(id);

            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;
        }

        model.addAttribute("detail", detail);
        model.addAttribute("editMode", editMode);

        return "admin/banner/add";
    }

    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String addSubmit(Model model, BannerInput parameter,
                            HttpServletRequest request,
                            MultipartFile file) {

        String saveFileName = "";
        String urlFileName = "";

        if (file != null) {
            String originalFileName = file.getOriginalFilename();

            String baseLocalPath = "/Users/ogeunhyeob/Desktop/spring-study/fastlms/files";
            String baseUrlPath = "/files";
            //String subPath = "banner";

            String[] arrFilename = FileUtil.getNewSaveFile(baseLocalPath, baseUrlPath, originalFileName);

            saveFileName = arrFilename[0];
            urlFileName = arrFilename[1];

            try {
                File newFile = new File(saveFileName);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info("#############################");
                log.info(e.getMessage());
            }
        }

        parameter.setFilename(saveFileName);
        parameter.setUrlFilename(urlFileName);

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();

            BannerDto existBanner = bannerService.getById(id);

            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = bannerService.set(parameter);

        } else {
            boolean result = bannerService.add(parameter);
        }

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String delete(BannerInput parameter) {

        boolean result = bannerService.delete(parameter.getIdList());

        return "redirect:/admin/banner/list.do";
    }
}
