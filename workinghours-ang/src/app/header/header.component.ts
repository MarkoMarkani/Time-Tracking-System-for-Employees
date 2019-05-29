import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
id;
  constructor( private employeeService:EmployeeService,private route: ActivatedRoute,
    private router: Router,private cookieService: CookieService) {
    
     }
  ngOnInit() {
  }

}
