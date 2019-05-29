import { Injectable } from '@angular/core';
import { Employee } from './model/employee.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private employees: Employee[];
  private baseUrl = 'http://localhost:8080/api/employees';

  constructor(private http: HttpClient) { }

  getEmployeesList() {
    return this.http.get('http://localhost:8080/api/employees');
  }
  getOvertimeEmployeesList() {
    
    return this.http.get('http://localhost:8080/api/workingdays/overtime');
  }
  getEmployee(employeeid: number) {
    return this.http.get(`${this.baseUrl}/${employeeid}`);
  }
  createEmployee(employee: Object): Observable<Object> {
    return this.http.post('http://localhost:8080/api/employees', employee);
  }
 
  updateEmployee(employee,employeeid) {
    return this.http.put(`http://localhost:8080/api/employees/${employeeid}`, employee);
  }

 

 createJob(job:Object): Observable<Object>{
   return this.http.post('http://localhost:8080/api/jobs', job);
 }

 getJobsList(){
   return this.http.get('http://localhost:8080/api/jobs');

 }
 getJobHistory(id){
  return this.http.get(`http://localhost:8080/api/employees/${id}/jobhistories`);

}
 getJobByName(name:string){
   return this.http.get('http://localhost:8080/api/jobs/search/{name}');

 }

getPaymentList(){
     return this.http.get('http://localhost:8080/api/payments');
}
createWorkingDay(checkIn,id){
 return this.http.post(`http://localhost:8080/api/employees/${id}/workingday`, checkIn);
}
updateWorkingDay(checkOut,id){
 return this.http.post(`http://localhost:8080/api/employees/${id}/workingday/checkout`, checkOut);
}
loginEmployee(employee): Observable<Employee> {
 return this.http.post(`http://localhost:8080/api/login`,employee);

}
getPaymentByEmployee(id){
 return this.http.get(`${this.baseUrl}/${id}/payments`);

}
getHolidays(){
 return this.http.get('http://localhost:8080/api/holidays');
}
getHolidaytype() {
 return this.http.get('http://localhost:8080/api/holidaytypes');
}
createHolidaytype(holidaytype){
 return this.http.post('http://localhost:8080/api/holidaytype', holidaytype);
}
createHolidays(holiday){
  console.log(holiday);
 return this.http.post('http://localhost:8080/api/holiday', holiday);
}
getWeekdays() {
 return this.http.get('http://localhost:8080/api/weekdays');
}
createBreak(started, id){
  return this.http.post(`http://localhost:8080/api/employees/${id}/workingday/break`, started);
 }
 updateBreak(ended, id){
  return this.http.put(`http://localhost:8080/api/employees/${id}/workingday/break/end`, ended);
 }
 getBreaksByEmployeeID(id){
  return this.http.get(`${this.baseUrl}/${id}/workingday/break`);
 }
}


