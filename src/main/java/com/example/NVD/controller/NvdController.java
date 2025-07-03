package com.example.NVD.controller;


import com.example.NVD.model.Cve;
import com.example.NVD.service.NvdService;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import java.util.List;

@Controller
public class NvdController {

    @Autowired
    private NvdService nvdService;

    /*@GetMapping("/cve-table1")
    public String showCveTable(Model model) {
        NvdResponse response = nvdService.fetchCveData();
        List<Vulnerability> vulnerabilities = response.getVulnerabilities();
        model.addAttribute("vulnerabilities", vulnerabilities);
        return "cve_table"; // maps to templates/cve_table.html
    }*/
    
    @GetMapping("/cves/list")
    public String listCves(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "publishedDate") String sort,
                           @RequestParam(defaultValue = "asc") String dir,
                           Model model) {

        Page<Cve> paginated = nvdService.getPaginatedCves(page, size, sort, dir);

        model.addAttribute("paginatedData", paginated.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalRecords", paginated.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("reverseDir", dir.equals("asc") ? "desc" : "asc");

        return "cve_table"; // HTML file name (cve_table.html)
    }
    
    @GetMapping("/cves/{id}")
    public String getCveDetails(@PathVariable String id, Model model) {
        Optional<Cve> cve = nvdService.getCveById(id); // ✅ using service layer

        if (cve.isEmpty()) {
            return "error/404"; // custom error page if needed
        }

        model.addAttribute("cve", cve.get());
        return "cve_detail"; // renders cve_detail.html
    }


    
    @GetMapping("/cve/fetch")
    public String fetchCves() {
        nvdService.fetchAndSaveCveData();
        return "redirect:/cves/list";
    }
    
    @GetMapping("/api/cves/id/{id}")
    @ResponseBody
    public List<Cve> getById(@PathVariable String id) {
        return nvdService.getCvesById(id);
    }

    @GetMapping("/api/cves/year/{year}")
    @ResponseBody
    public List<Cve> getByYear(@PathVariable int year) {
        return nvdService.getCvesByYear(year);
    }

    @GetMapping("/api/cves/score/{score}")
    @ResponseBody
    public List<Cve> getByScore(@PathVariable double score) {
        return nvdService.getCvesByScore(score);
    }

    @GetMapping("/api/cves/modified-since/{days}")
    @ResponseBody
    public List<Cve> getByModifiedSince(@PathVariable int days) {
        return nvdService.getCvesModifiedSince(days);
    }
    
    @GetMapping("/cves/fetch")
    public String fetchCveData() {
        nvdService.fetchAndSaveCveData();
        return "redirect:/cves/list";
    }



}

