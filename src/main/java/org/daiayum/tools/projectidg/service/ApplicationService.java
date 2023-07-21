package org.daiayum.tools.projectidg.service;

import java.util.Date;
import java.util.List;

import org.daiayum.tools.projectidg.api.ApplicationDTO;
import org.daiayum.tools.projectidg.model.Application;
import org.daiayum.tools.projectidg.model.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationsRepository repository;

	public Boolean isValid(final Application application) {
		if (application != null && !application.getApplicantName().isEmpty()) {
			return true;
		}
		return false;
	}

	public Page<Application> getAllApplications(Pageable page) {
		return this.repository.findAllByOrderByIdDesc(page);
	}

	public Application createApplication(final Application application) {
		return this.repository.save(application);
	}

	@Transactional
	public Application updateApplication(final Application application) {
		/*
		 * return this.repository.findById(application.getId()).flatMap(t -> {
		 * t.setName(application.getName()); return this.repository.save(t); });
		 */
		return null;
	}

	@Transactional
	public String deleteApplication(final long id) {
		this.repository.deleteById(id);
		return id + "deleted.";
	}

	public Application generateNextApplication(ApplicationDTO applicationDto) {
		Application application = repository.findFirstByOrderByIdDesc();
		Long nextNumber = null;
		if (application == null) {
			nextNumber = 1l;
		}else {
			nextNumber = Long.parseLong(application.getApplicationNo().substring(application.getApplicationNo().lastIndexOf("/") + 1)) + 1;
		}
		
		String nextApplicationNumber = "DPK/IC/" + nextNumber;
		
		Application nextApplication = new Application();
		nextApplication.setApplicantName(applicationDto.getApplicantName());
		nextApplication.setApplicationNo(nextApplicationNumber);
		nextApplication.setGeneratedDate(new Date());
		nextApplication.setDevice(applicationDto.getDevice());
		
		nextApplication.setDuration(applicationDto.getDuration());
		nextApplication.setFee(applicationDto.getFee());
		nextApplication.setImei(applicationDto.getImei());
		nextApplication.setMobile(applicationDto.getMobile());
		nextApplication.setReason(applicationDto.getReason());
		
		
		return repository.save(nextApplication);
	}

}