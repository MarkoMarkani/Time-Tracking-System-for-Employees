import { Employee } from './employee.model';
import { Jobhistory } from './jobhistory.model';



export class Job{
    jobid:number;
    commissionpct:number;
    name:string;
    employees: Array<Employee>;
    jobhistories: Array<Jobhistory>;
}