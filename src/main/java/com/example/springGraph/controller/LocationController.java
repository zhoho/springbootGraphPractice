package com.example.springGraph.controller;
import com.example.springGraph.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LocationController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/")
    public String index() {
        return "index"; // 타임리프 템플릿 이름 (index.html)
    }

    @PostMapping("/getCoordinates")
    public String getCoordinates(@RequestParam("file") MultipartFile file,
                                 @RequestParam("level1") String level1,
                                 @RequestParam(value = "level2", required = false) String level2,
                                 @RequestParam(value = "level3", required = false) String level3,
                                 Model model) {
        ExcelService.GridCoordinates coordinates = excelService.getGridCoordinates(file, level1, level2, level3);

        if (coordinates != null) {
            model.addAttribute("gridX", coordinates.getX());
            model.addAttribute("gridY", coordinates.getY());
        } else {
            model.addAttribute("message", "No matching data found.");
        }

        return "index"; // 타임리프 템플릿으로 다시 보냄
    }
}
