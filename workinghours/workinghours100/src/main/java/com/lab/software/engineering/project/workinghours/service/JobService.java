
package com.lab.software.engineering.project.workinghours.service;
import java.util.List;


import com.lab.software.engineering.project.workinghours.entity.Job;




public interface JobService {
	public void saveJob(Job theJob);
	public List<Job> getAll();
	public void deleteJob(Job job);
}