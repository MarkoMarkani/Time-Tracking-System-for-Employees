package com.lab.software.engineering.project.workinghours.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.software.engineering.project.workinghours.entity.Break;
import com.lab.software.engineering.project.workinghours.service.BreakService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BreakRestController {
	
	@Autowired
	private BreakService breakService;
	
	@PostMapping("/employees/{employeeid}/workingday/break")
	public Break saveBreak(@RequestBody Break br, @PathVariable long employeeid) {

		return breakService.saveBreak(br, employeeid);
		
	}
	
	@PutMapping("/employees/{employeeid}/workingday/break/end")
	    public Break updateBreak(@RequestBody Object checkout1, @PathVariable long employeeid) {
	       
	        return breakService.updateBreak(checkout1, employeeid);
	        
	}
	@GetMapping("/employees/{employeeid}/workingday/break")
    public List<Break> getBreaksByEmployeeID(@PathVariable long employeeid) {
       
        return breakService.getBreaksByEmployeeID(employeeid);
        
}
}
