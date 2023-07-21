package org.daiayum.tools.projectidg.api;

import java.math.BigDecimal;
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
		setDevice(application.getDevice());
		
		setDuration(application.getDuration());
		setFee(application.getFee());
		setImei(application.getImei());
		setMobile(application.getMobile());
		setReason(application.getReason());
	}
	
	private String applicationNo;
	private String generatedDate;
	private String applicantName;
	private String reason;
	private Integer duration;
	private String mobile;
	private String imei;
	private String device;
	private BigDecimal fee;
	
	
}
