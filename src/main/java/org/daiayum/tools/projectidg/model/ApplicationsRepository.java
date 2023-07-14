package org.daiayum.tools.projectidg.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {
	
	public Application findFirstByOrderByIdDesc();
	
	Page<Application> findAllByOrderByIdDesc(Pageable page);
}