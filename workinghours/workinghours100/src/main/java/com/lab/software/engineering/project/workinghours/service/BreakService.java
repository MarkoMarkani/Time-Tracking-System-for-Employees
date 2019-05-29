package com.lab.software.engineering.project.workinghours.service;

import java.util.List;

import com.lab.software.engineering.project.workinghours.entity.Break;

public interface BreakService {

	public Break saveBreak(Break br, long employeeid);
	public Break updateBreak(Object br, long employeeid);
	public List<Break> getBreaksByEmployeeID(long employeeid);
}
