import { Employee } from './employee.model';
import { Job } from './job.model';

export class Jobhistory {
    jobhistoryid:number;
    enddate:Date;
    startdate:Date;
    employee:Employee;
    job:Job;

}