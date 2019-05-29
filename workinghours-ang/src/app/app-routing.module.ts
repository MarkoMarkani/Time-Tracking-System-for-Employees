import { AddjobComponent } from './employee/addjob/addjob.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeewelcomeComponent } from './employee/employeewelcome/employeewelcome.component';
import { EmployeedetailsComponent } from './employee/employeedetails/employeedetails.component';
import { EmployeerecordComponent } from './employee/employeerecord/employeerecord.component';
import { EditinfoComponent } from './employee/editinfo/editinfo.component';
import { EmployeepaymentComponent } from './employee/employeepayment/employeepayment.component';
import { EmployeestatsComponent } from './employee/employeestats/employeestats.component';
import { EmployeeholidayComponent } from './employee/employeeholiday/employeeholiday.component';
import { EmployeevacationComponent } from './employee/employeevacation/employeevacation.component';
import { EmployeelistComponent } from './employee/employeelist/employeelist.component';

import { AddemployeeComponent } from './employee/addemployee/addemployee.component';
import { AddholidayComponent } from './employee/addholiday/addholiday.component';
import { AddvacationComponent } from './employee/addvacation/addvacation.component';
import { AddholidaytypeComponent } from './employee/addholidaytype/addholidaytype.component';
import { AddholidayperemployeeComponent } from './employee/addholidayperemployee/addholidayperemployee.component';
import { ExecutepaymentComponent } from './employee/executepayment/executepayment.component';
import { HolidayperemployeeComponent } from './employee/holidayperemployee/holidayperemployee.component';
import { JoblistComponent } from './employee/joblist/joblist.component';
import { HolidaytypeComponent } from './employee/holidaytype/holidaytype.component';
import { WorkingdaysComponent } from './employee/workingdays/workingdays.component';
import { UpdatejobComponent } from './employee/updatejob/updatejob.component';
import { JobhistoryComponent } from './employee/jobhistory/jobhistory.component';
import { PaymentlistComponent } from './employee/paymentlist/paymentlist.component';
import { VacationlistComponent } from './employee/vacationlist/vacationlist.component';
import { HolidaylistComponent } from './employee/holidaylist/holidaylist.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'employee/:id', component: EmployeeComponent, children: [
    { path: 'info', component: EmployeedetailsComponent },
    { path: 'addholidayperemployee', component: AddholidayperemployeeComponent },
    { path: 'executepayment', component: ExecutepaymentComponent },
    { path: 'holidayperemployee', component: HolidayperemployeeComponent },
   
    { path: 'workingdays', component: WorkingdaysComponent },
    { path: 'updatejob', component: UpdatejobComponent },
    { path: 'jobhistory', component: JobhistoryComponent },
   
    { path: 'welcome', component: EmployeewelcomeComponent },
    { path: 'payment',component: EmployeepaymentComponent },
    { path: 'paymentlist',component: PaymentlistComponent },
    { path: 'executepayment',component: ExecutepaymentComponent },
    { path: 'record',component: EmployeerecordComponent },
    { path: 'editinfo', component: EditinfoComponent },
    
    { path: 'stats', component: EmployeestatsComponent },
    { path: 'employeelist', component: EmployeelistComponent },
    { path: 'addemployee', component: AddemployeeComponent },
    { path: 'vacation', component: EmployeevacationComponent },
    { path: 'vacationlist', component: VacationlistComponent },
    { path: 'holiday', component: EmployeeholidayComponent },

    { path: 'holidaylist', component: HolidaylistComponent },
    
    { path: 'addholiday', component: AddholidayComponent },
    { path: 'addvacation', component: AddvacationComponent },
    { path: 'addjob', component: AddjobComponent },
    { path: 'joblist', component: JoblistComponent },
    { path: 'holidaytype', component: HolidaytypeComponent },
    { path: 'addholidaytype', component: AddholidaytypeComponent },


  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
