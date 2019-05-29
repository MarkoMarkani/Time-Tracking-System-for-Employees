import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private baseUrl = 'http://localhost:8080/api';
  constructor(private http:HttpClient) { }

  getPaymentList(){
    return this.http.get('http://localhost:8080/api/payments');
}
getSinglePayment(employeeid:number){
  return this.http.get(`http://localhost:8080/api/employees/${employeeid}/payments`);
}

savePayment(payment,id){
  console.log("Save Payment: " + payment +" EID: " + id);
  return this.http.post(`http://localhost:8080/api/employees/${id}/payment`,payment);
}
}
