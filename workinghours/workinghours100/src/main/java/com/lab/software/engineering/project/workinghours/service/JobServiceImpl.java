package com.lab.software.engineering.project.workinghours.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab.software.engineering.project.workinghours.dao.JobRepository;
import com.lab.software.engineering.project.workinghours.entity.Job;



@Service
public class JobServiceImpl implements JobService {

//inject job repository
	@Autowired
	private JobRepository jobRepository;


	@Override
	
	public void saveJob(Job job) {

		jobRepository.save(job);
	}
	
	@Override
	
	public List<Job> getAll() {

		return jobRepository.findAll();
	}

	@Override
	public void deleteJob(Job job) {
	
		jobRepository.delete(job);
	}
}





