
import { Job } from './job.model';
import { Vacation } from './vacation.model';
import { Workingday} from './workingday.model';

export class Employee{
    constructor(
        public employeeid?:number,
        public active?:boolean,
        public username?:string,
        public hiredate?:Date,
        public password?:string,
        public email?:string,
        public firstname?:string,
        public lastname?:string,
        public salary?:number,
        public job?:Job,
        public vacations?: Array<Vacation>,
        public workingdays?: Array<Workingday>
        ){}

}