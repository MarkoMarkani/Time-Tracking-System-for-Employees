import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  constructor(private http: HttpClient) { }

  getVacationList(id) {
    return this.http.get(`http://localhost:8080/api/employees/${id}/vacations`);
  }
  getVacations(){
    return this.http.get(`http://localhost:8080/api/vacations`);
  }
  createVacation(vacation,id){
    return this.http.post(`http://localhost:8080/api/employees/${id}/vacation`,vacation);
  }
  
}
