package com.saeipman.app.ocrTest.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.saeipman.app.ocrTest.config.OcrApi;
import com.saeipman.app.ocrTest.config.OcrUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UploadController {
    @Value("${naver.service.secretKey}")
    private String secretKey;
    private final OcrApi naverApi;
    private final OcrUtil ocrUtil;

    // 파일 업로드 폼을 보여주기 위한 GET 요청 핸들러 메서드
    @GetMapping("/upload-form")
    public String uploadForm() throws Exception {
        return "ocr/upload-form"; // HTML 템플릿의 이름을 반환 (upload-form.html)
    }

    // 파일 업로드 및 OCR 수행을 위한 POST 요청 핸들러 메서드
    @PostMapping("/uploadAndOcr")
    public String uploadAndOcr(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (file.isEmpty()) {
            return "error"; // 파일이 비어있을 경우 에러를 처리하는 HTML 템플릿으로 이동
        }

        String naverSecretKey = secretKey; // 본인의 네이버 Clova OCR 시크릿 키로 대체

        File tempFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(tempFile);

        List<String> result = naverApi.callApi("POST", tempFile.getPath(), naverSecretKey, "jpg");

        tempFile.delete(); // 임시 파일 삭제
        
		/*
		 * //ocr 병합 String merged = ocrUtil.mergeWords(result);
		 * 
		 * //원하는 정보 String doro = ocrUtil.extractValue(merged, "도로명주소"); String area =
		 * ocrUtil.extractValue(merged, "건축면적"); String saede =
		 * ocrUtil.extractValue(merged, "세대수");
		 * 
		 * model.addAttribute("doro",doro); model.addAttribute("area",area);
		 * model.addAttribute("saede",saede);
		 */
        // "도로명주소" 키워드를 기준으로 전체 주소 추출
        String fullAddress = ocrUtil.mergeWords(result, "도로명주소");

        model.addAttribute("fullAddress", fullAddress);


        model.addAttribute("ocrResult", result); // OCR 결과를 HTML 템플릿에 전달

        return "ocr/ocr-result"; // OCR 결과를 표시하는 HTML 템플릿 이름 반환
    }
    
}
