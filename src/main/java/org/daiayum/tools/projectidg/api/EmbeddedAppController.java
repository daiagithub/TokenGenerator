package org.daiayum.tools.projectidg.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.daiayum.tools.projectidg.model.Application;
import org.daiayum.tools.projectidg.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Tasks controller class
@Controller
class EmbeddedAppController {

	@Autowired
	private ApplicationService service;

	@GetMapping("/")
	public String viewHomePage(Model model, @RequestParam(defaultValue = "0") int page) {
		Page<Application> applications = service.getAllApplications(PageRequest.of(page, 10));
		model.addAttribute("applications", applications);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", applications.getTotalPages());
		
		 if (applications.getTotalPages() > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(0, applications.getTotalPages() - 1)
	                .boxed()
	                .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
		System.out.println(applications.getTotalPages());
		System.out.println(applications.getTotalElements());
		System.out.println(applications.getContent().size());
		return "index";
	}

	@GetMapping("/generatenew")
	public String generateNewTicket(Model model) {
		Application application = new Application();
		model.addAttribute("ticket", application);
		return "newTicket";
	}

	@PostMapping("/save")
	public String saveTicket(@ModelAttribute("ticket") ApplicationDTO applicationDto, Model model) {
		ApplicationDTO result = new ApplicationDTO(service.generateNextApplication(applicationDto));
		System.out.println(result);
		model.addAttribute("ticket", result);
		return "viewApplication";
	}
}