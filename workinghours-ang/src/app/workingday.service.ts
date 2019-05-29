import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WorkingdayService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

 
  getWorkingdays(id) {
    return this.http.get(`http://localhost:8080/api/employees/${id}/workingdays`);
  }
  getWorkingdaysByEmployee(employeeid:number){
    return this.http.get(`http://localhost:8080/api/employees/${employeeid}/workingdays`);
  }
}
