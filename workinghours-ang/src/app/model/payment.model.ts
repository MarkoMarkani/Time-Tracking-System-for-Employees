import { Employee } from './employee.model';

export class Payment{
    paymentid:number;
    overtime:number;
    fromdate:Date;
    todate:Date;
    total:number;
    employee:Employee;

}