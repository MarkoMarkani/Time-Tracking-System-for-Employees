import { Employee } from './employee.model';
import { Holidaytype } from './holidaytype.model';
import { WeekDay } from '@angular/common';

export class Holidayperemployee {
    constructor(
        public holidayperemployeeid?: number,
        public date?: Date,
        public employee?: Employee,
        public name?: string,
        public holidaytype?:Holidaytype|null|string,
        public weekday?: WeekDay
    ) { }
}