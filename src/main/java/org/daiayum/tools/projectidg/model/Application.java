package org.daiayum.tools.projectidg.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Entity
@Data
public class Application {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String applicationNo;
	private Date generatedDate;
	private String applicantName;
	
	private String reason;
	private Integer duration;
	private String mobile;
	private String imei;
	private String device;
	private BigDecimal fee;
	
}
