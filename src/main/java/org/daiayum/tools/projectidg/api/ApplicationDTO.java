package org.daiayum.tools.projectidg.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.daiayum.tools.projectidg.model.Application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ApplicationDTO {
	
	public ApplicationDTO(Application application) {
		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a z");
		this.setApplicantName(application.getApplicantName());
		this.setApplicationNo(application.getApplicationNo());
		this.setGeneratedDate(formatter.format(application.getGeneratedDate()));
	}
	
	private String applicationNo;
	private String generatedDate;
	private String applicantName;
	
}
