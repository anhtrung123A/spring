package com.example.AES.controller;

import com.example.AES.entity.ContextHolder;
import com.example.AES.entity.FileFormat;
import com.example.AES.service.AesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1")
@Log
public class Controller {
    private final AesService aesService;
    @GetMapping(path = "/get-key")
    public String getKey() throws NoSuchAlgorithmException {
        return Base64.getEncoder().encodeToString(aesService.getKey().getEncoded());
    }
    @PostMapping(path = "/encrypt")
    public String encrypt(@RequestBody ContextHolder contextHolder) throws Exception{
        return aesService.encrypt(contextHolder.getText(), contextHolder.getSecretKey());
    }

    @PostMapping(path = "/decrypt")
    public String decrypt(@RequestBody ContextHolder contextHolder) throws Exception {
        return aesService.decrypt(contextHolder.getText(), contextHolder.getSecretKey());
    }

    @PostMapping(path = "/get-format")
    public FileFormat getFormat(@RequestParam("file") MultipartFile file){
        String text = "";
        boolean isBold = false;
        boolean isItalic = false;
        boolean isUnderline = false;
        String color = "";
        try  {
            File tempFile = File.createTempFile("temp", null);
            try (OutputStream os = new FileOutputStream(tempFile)) {
                os.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            XWPFDocument document = new XWPFDocument(fileInputStream);
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    text = run.text();
                    isBold = run.isBold();
                    isItalic = run.isItalic();
                    isUnderline = run.getUnderline() != UnderlinePatterns.NONE;

                    color = run.getColor();

                    log.info("Text: " + text);
                    log.info("Bold: " + isBold);
                    log.info("Italic: " + isItalic);
                    log.info("Underline: " + isUnderline);
                    log.info("Color: " + (color != null ? color : "default"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileFormat.builder()
                .text(text)
                .color(color)
                .isBold(isBold)
                .isUnderline(isUnderline)
                .isItalic(isItalic)
                .build();
    }
    @PostMapping(path = "/get-html")
    public String getHtml(@RequestParam("file") MultipartFile file){
        String html = "";
        try  {
            File tempFile = File.createTempFile("temp", null);
            try (OutputStream os = new FileOutputStream(tempFile)) {
                os.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
            FileInputStream fileInputStream = new FileInputStream(tempFile);
            XWPFDocument document = new XWPFDocument(fileInputStream);
            StringBuilder htmlContent = new StringBuilder();
            for (XWPFParagraph paragraph : document.getParagraphs()){
                htmlContent.append("<p>");
                for (XWPFRun xwpfRun : paragraph.getRuns()){
                    String style = aesService.getRunStyle(xwpfRun);
                    htmlContent.append("<span").append(style).append(">")
                            .append(xwpfRun.text()).append("</span>");
                }
                htmlContent.append("</p>");
            }
            html = htmlContent.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }
}
