package com.example.NVD.service;

import com.example.NVD.model.Cve;
import com.example.NVD.model.CveObject;
import com.example.NVD.model.CveDescription;
import com.example.NVD.model.NvdResponse;
import com.example.NVD.model.Vulnerability;
import com.example.NVD.repository.NVDRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

//import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*@Service
public class NvdService {
	@Autowired
	private NVDRepository repo;
	
	private final String NVD_API_URL = "https://services.nvd.nist.gov/rest/json/cves/2.0";
	
	public void fetchCveData() {
		RestTemplate restTemplate = new RestTemplate();
		NvdResponse[] l=restTemplate.getForObject(NVD_API_URL, NvdResponse[].class);
		if(l!=null) {
		  repo.saveAll(Arrays.asList(l));
		}
	}
}*/

@Service
public class NvdService {

    @Autowired
    private NVDRepository cveRepo;

    private final String NVD_API_URL = "https://services.nvd.nist.gov/rest/json/cves/2.0";

    public void fetchAndSaveCveData() {
        RestTemplate restTemplate = new RestTemplate();
        NvdResponse response = restTemplate.getForObject(NVD_API_URL, NvdResponse.class);

        if (response != null && response.getVulnerabilities() != null) {
            List<Cve> cvesToSave = new ArrayList<>();

            for (Vulnerability v : response.getVulnerabilities()) {
                CveObject cveObj = v.getCve(); // JSON-mapped class

                Cve cve = new Cve();
                cve.setId(cveObj.getId());

                // Convert dates
                if (cveObj.getPublished() != null)
                    cve.setPublishedDate(LocalDate.parse(cveObj.getPublished().substring(0, 10)));

                if (cveObj.getLastModified() != null)
                    cve.setLastModifiedDate(LocalDate.parse(cveObj.getLastModified().substring(0, 10)));

                // Base score
                Double baseScore = null;
                if (cveObj.getMetrics() != null) {
                    if (cveObj.getMetrics().getCvssMetricV3() != null && !cveObj.getMetrics().getCvssMetricV3().isEmpty()) {
                        baseScore = cveObj.getMetrics().getCvssMetricV3().get(0).getCvssData().getBaseScore();
                    } else if (cveObj.getMetrics().getCvssMetricV2() != null && !cveObj.getMetrics().getCvssMetricV2().isEmpty()) {
                        baseScore = cveObj.getMetrics().getCvssMetricV2().get(0).getCvssData().getBaseScore();
                    }
                }
                cve.setBaseScore(baseScore);

                // Descriptions
                List<CveDescription> descriptionList = new ArrayList<>();
                if (cveObj.getDescriptions() != null) {
                    for (CveDescription d : cveObj.getDescriptions()) {
                        if ("en".equalsIgnoreCase(d.getLang())) {
                            CveDescription desc = new CveDescription();
                            desc.setLang(d.getLang());
                            desc.setValue(d.getValue());
                            desc.setCve(cve); // set back-reference
                            descriptionList.add(desc);
                        }
                    }
                }

                cve.setDescriptions(descriptionList);
                cvesToSave.add(cve);
            }

            cveRepo.saveAll(cvesToSave); // Save to DB
        }
    }

    

                // Descriptions
    public void fetchCveData() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://services.nvd.nist.gov/rest/json/cves/2.0?resultsPerPage=2000";

        NvdResponse response = restTemplate.getForObject(url, NvdResponse.class);

        if (response != null && response.getVulnerabilities() != null) {
            List<Cve> cvesToSave = new ArrayList<>();

            for (Vulnerability vuln : response.getVulnerabilities()) {
                CveObject cveObj = vuln.getCve();

                Cve cve = new Cve();
                cve.setId(cveObj.getId());

                if (cveObj.getPublished() != null) {
                    cve.setPublishedDate(LocalDate.parse(cveObj.getPublished().substring(0, 10)));
                }
                if (cveObj.getLastModified() != null) {
                    cve.setLastModifiedDate(LocalDate.parse(cveObj.getLastModified().substring(0, 10)));
                }

                // Set baseScore
                Double baseScore = null;
                if (cveObj.getMetrics() != null) {
                    if (cveObj.getMetrics().getCvssMetricV3() != null && !cveObj.getMetrics().getCvssMetricV3().isEmpty()) {
                        baseScore = cveObj.getMetrics().getCvssMetricV3().get(0).getCvssData().getBaseScore();
                    } else if (cveObj.getMetrics().getCvssMetricV2() != null && !cveObj.getMetrics().getCvssMetricV2().isEmpty()) {
                        baseScore = cveObj.getMetrics().getCvssMetricV2().get(0).getCvssData().getBaseScore();
                    }
                }
                cve.setBaseScore(baseScore);

                // Set descriptions (only English)
                List<CveDescription> descriptionList = new ArrayList<>();
                if (cveObj.getDescriptions() != null) {
                    for (CveDescription d : cveObj.getDescriptions()) {
                        if ("en".equalsIgnoreCase(d.getLang())) {
                            CveDescription desc = new CveDescription();
                            desc.setLang(d.getLang());
                            desc.setValue(d.getValue());
                            desc.setCve(cve);  // associate
                            descriptionList.add(desc);
                        }
                    }
                }
                cve.setDescriptions(descriptionList);
                System.out.println("Saving CVE: " + cve.getId() + " | Published: " + cve.getPublishedDate() + " | Score: " + cve.getBaseScore());
                cvesToSave.add(cve);
            }
              
            cveRepo.saveAll(cvesToSave);
        }
    }
               
    

    
    public Page<Cve> getPaginatedCves(int page, int size, String sortField, String dir) {
        Sort sort = dir.equalsIgnoreCase("desc")
            ? Sort.by(sortField).descending()
            : Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return cveRepo.findAll(pageable);
    }
  
    public Optional<Cve> getCveById(String id) {
        return cveRepo.findById(id);
    }
    
    public List<Cve> getCvesById(String id) {
        return cveRepo.findByIdContainingIgnoreCase(id);
    }

    public List<Cve> getCvesByYear(int year) {
        return cveRepo.findByPublishedYear(year);
    }

    public List<Cve> getCvesByScore(double score) {
        return cveRepo.findByBaseScoreGreaterThanEqual(score);
    }

    public List<Cve> getCvesModifiedSince(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        return cveRepo.findByLastModifiedSince(cal.getTime());
    }


}
