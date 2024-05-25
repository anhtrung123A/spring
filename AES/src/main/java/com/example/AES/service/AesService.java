package com.example.AES.service;

import lombok.extern.java.Log;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Service
@Log
public class AesService {
    public SecretKey getKey() throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance("AES").generateKey();
    }
    public SecretKey keyDecrypt(String encodedKey){
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
    public String encrypt(String text, String secretKey) throws Exception{
        Cipher encrypt = Cipher.getInstance("AES");
        encrypt.init(Cipher.ENCRYPT_MODE, keyDecrypt(secretKey));
        byte[] encodedText = encrypt.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encodedText);
    }

    public String decrypt(String encodedText, String secretKey) throws Exception {
        Cipher decrypt = Cipher.getInstance("AES");
        decrypt.init(Cipher.DECRYPT_MODE, keyDecrypt(secretKey));
        return new String(decrypt.doFinal(Base64.getDecoder().decode(encodedText)));
    }

    public String getRunStyle(XWPFRun run) {
        StringBuilder style = new StringBuilder(" style=\"");

        // Font family
        String fontFamily = run.getFontFamily();
        if (fontFamily != null && !fontFamily.isEmpty()) {
            style.append("font-family: ").append(fontFamily).append(";");
        }
        else {
            style.append("font-family: ").append("Calibri").append(";");
        }

        // Font size
        if (run.getFontSizeAsDouble() != null){
            int fontSize = run.getFontSizeAsDouble().intValue();
            style.append("font-size: ").append(fontSize).append("pt;");
        }
        // Other styling properties (e.g., bold, italic, underline, color, etc.)
        if (run.isBold()) {
            style.append("font-weight: bold;");
        }
        if (run.isItalic()) {
            style.append("font-style: italic;");
        }
        if (run.isStrikeThrough()) {
            style.append("text-decoration: line-through;");
        }
        if (run.getColor() != null) {
            style.append("color: #").append(run.getColor()).append(";");
        }
        if (run.getUnderline() != UnderlinePatterns.NONE) {
            style.append("text-decoration: underline;");
        }
        if (!Objects.equals(String.valueOf(run.getTextHighlightColor()), "none")){
            style.append("background-color: ").append(run.getTextHighlightColor()).append(";");
        }
        style.append("\"");
        return style.toString();
    }
}
