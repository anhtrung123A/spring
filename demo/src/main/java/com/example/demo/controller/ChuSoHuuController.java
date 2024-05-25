package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ChuSoHuu;
import com.example.demo.service.ChuSoHuuService;

@Controller
public class ChuSoHuuController {
	
	private ChuSoHuuService chuSoHuuService;
	
	@Autowired
	public ChuSoHuuController(ChuSoHuuService chuSoHuuService) {
		this.chuSoHuuService = chuSoHuuService;
	}
	
	@GetMapping("/thongtinkhoa")
	public String thongtinkhoa() {
		return "thongtinkhoa";
	}
	
	
	@GetMapping("/register-key")
	public String dangkikhoaForm(Model model) {
		model.addAttribute("ChuSoHuu", new ChuSoHuu());
		
		return "register-key";
	}
	
	@PostMapping("/upload") 
	public String upload(Model model, @RequestParam("file") String file) throws IOException {
		ChuSoHuu chuSoHuu = chuSoHuuService.findByPublicKey(file);
		if(chuSoHuu != null) {
			model.addAttribute("csh", chuSoHuu);
		} else {
			model.addAttribute("mes", "Không có thông tin về public key này.");
		}
		return "thongtinkhoa";
    }
	
	@PostMapping("/register") 
	public String registerKey(@ModelAttribute("ChuSoHuu") ChuSoHuu chuSoHuu, Model model) throws IOException {
		chuSoHuu.setId(0);
		try {
			ChuSoHuu dbchuSoHuu = chuSoHuuService.save(chuSoHuu);
			model.addAttribute("mes", "Đăng kí thành công");
			model.addAttribute("csh", dbchuSoHuu);
		} catch (Exception e) {
			model.addAttribute("mes", "Đăng kí thất bại");
		}
		
		 
		return "register-key";
    }
}
