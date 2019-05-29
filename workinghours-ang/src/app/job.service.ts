import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  constructor(private http:HttpClient) { }
  createJob(job) {
    return this.http.post('http://localhost:8080/api/jobs', job);
  }
  updateJob(job, id){

    return this.http.put(`http://localhost:8080/api/employees/${id}/job`, job);
  }
  getJobsList() {
    return this.http.get('http://localhost:8080/api/jobs');

  }
}
