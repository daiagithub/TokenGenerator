package org.daiayum.tools.projectidg.api;

import java.util.List;

import org.daiayum.tools.projectidg.model.Application;
import org.daiayum.tools.projectidg.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

// Tasks controller class
@RestController
@RequestMapping("/applications")
class ApplicationsController {

	@Autowired
	private ApplicationService service;

	/*
	 * @GetMapping() public ResponseEntity<List<Application>> get() { return
	 * ResponseEntity.ok(this.service.getAllApplications()); }
	 */
	
	@GetMapping(path = "/next")
	public ResponseEntity<String> next(@RequestParam("name") String name) throws JsonProcessingException {
		ApplicationDTO applicationDto = new ApplicationDTO();
		applicationDto.setApplicantName(name);
		ApplicationDTO result = new ApplicationDTO(service.generateNextApplication(applicationDto));
		
		StringBuilder resultString = new StringBuilder();
		resultString = resultString.append("<table style='font-family: arial; font-size: 12px'>");
		resultString = resultString.append("<tr><td colspan='2' valign='bottom'>INTERNET USAGE TOKEN</td>");
		resultString = resultString.append("<td colspan='1' style='text-align: right' valign='bottom'>" + result.getApplicationNo() + "</td></tr>");
		resultString = resultString.append("<tr><td colspan=3><hr></td></tr>");
		//resultString = resultString.append("<tr><td colspan='3' style='font-size: 18px; font-weight: bold'>" + result.getApplicantName() + "</td></tr>");
		resultString = resultString.append("<tr><td>User Full Name</td><td>:</td><td>" + result.getApplicantName() + "</td></tr>");
		resultString = resultString.append("<tr><td>Time of Entry</td><td>:</td><td>" + result.getGeneratedDate().toUpperCase() + "</td></tr>");
		resultString = resultString.append("<tr><td>Device/Model</td><td>:</td></tr>");
		resultString = resultString.append("<tr><td>IMEI No</td><td>:</td></tr>");
		resultString = resultString.append("<tr><td colspan=3><hr></td></tr></table>");
		return ResponseEntity.ok(resultString.toString());
	}

	@PostMapping()
	public ResponseEntity<Application> post(@RequestBody Application application) {
		if (service.isValid(application)) {
			return ResponseEntity.ok(this.service.createApplication(application));
		}
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	}

	@PutMapping()
	public ResponseEntity<Application> put(@RequestBody Application application) {
		if (service.isValid(application)) {
			return ResponseEntity.ok(this.service.updateApplication(application));
		}
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	}

	@DeleteMapping()
	public ResponseEntity<String> delete(@RequestParam long id) {
		if (id > 0) {
			return ResponseEntity.ok(this.service.deleteApplication(id));
		}
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	}
}