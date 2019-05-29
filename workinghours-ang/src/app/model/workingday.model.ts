import { Employee } from './employee.model';
import { Weekday } from './weekday.model';


export class Workingday{
    workingdayid: number;
    checkin:Date;
    checkout:Date;
    employee: Employee;
    weekday: Weekday;
    workDuration?:number;

}