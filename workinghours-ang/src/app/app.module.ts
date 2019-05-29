import { SearchPipe } from './model/searchPipe';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { CookieService } from 'ngx-cookie-service';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeedetailsComponent } from './employee/employeedetails/employeedetails.component';
import { EmployeewelcomeComponent } from './employee/employeewelcome/employeewelcome.component';
import { EmployeerecordComponent } from './employee/employeerecord/employeerecord.component';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EditinfoComponent } from './employee/editinfo/editinfo.component';
import { EmployeepaymentComponent } from './employee/employeepayment/employeepayment.component';
import { EmployeestatsComponent } from './employee/employeestats/employeestats.component';
import { EmployeeholidayComponent } from './employee/employeeholiday/employeeholiday.component';
import { EmployeevacationComponent } from './employee/employeevacation/employeevacation.component';
import { EmployeelistComponent } from './employee/employeelist/employeelist.component';
import { ByemployeeComponent } from './employee/byemployee/byemployee.component';
import { AddemployeeComponent } from './employee/addemployee/addemployee.component';
import { SubheaderComponent } from './employee/subheader/subheader.component';
import { AddholidayComponent } from './employee/addholiday/addholiday.component';
import { AddjobComponent } from './employee/addjob/addjob.component';
import { AddvacationComponent } from './employee/addvacation/addvacation.component';
import { AddholidayperemployeeComponent } from './employee/addholidayperemployee/addholidayperemployee.component';
import { AddholidaytypeComponent } from './employee/addholidaytype/addholidaytype.component';
import { ExecutepaymentComponent } from './employee/executepayment/executepayment.component';
import { HolidayperemployeeComponent } from './employee/holidayperemployee/holidayperemployee.component';
import { HolidaytypeComponent } from './employee/holidaytype/holidaytype.component';
import { JoblistComponent } from './employee/joblist/joblist.component';
import { UpdatejobComponent } from './employee/updatejob/updatejob.component';
import { WorkingdaysComponent } from './employee/workingdays/workingdays.component';
import { JobhistoryComponent } from './employee/jobhistory/jobhistory.component';
import { WorkingdayComponent } from './employee/workingday/workingday.component';
import { PaymentlistComponent } from './employee/paymentlist/paymentlist.component';
import { VacationlistComponent } from './employee/vacationlist/vacationlist.component';
import { HolidaylistComponent } from './employee/holidaylist/holidaylist.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    EmployeeComponent,
    EmployeedetailsComponent,
    EmployeewelcomeComponent,
    EmployeerecordComponent,
    HomeComponent,
    EditinfoComponent,
    EmployeepaymentComponent,
    EmployeestatsComponent,
    EmployeeholidayComponent,
    EmployeevacationComponent,
    EmployeelistComponent,
    SearchPipe,
    ByemployeeComponent,
    AddemployeeComponent,
    SubheaderComponent,
    AddholidayComponent,
    AddjobComponent,
    AddvacationComponent,
    AddholidayperemployeeComponent,
    AddholidaytypeComponent,
    ExecutepaymentComponent,
    HolidayperemployeeComponent,
    HolidaytypeComponent,
    JoblistComponent,
    UpdatejobComponent,
    WorkingdaysComponent,
    JobhistoryComponent,
    WorkingdayComponent,
    PaymentlistComponent,
    VacationlistComponent,
    HolidaylistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
