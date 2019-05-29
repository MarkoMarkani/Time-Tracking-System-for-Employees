import { Weekday } from './weekday.model';
import { Holidaytype } from './holidaytype.model';


export class Holiday{
    constructor(
    public holidayid?:number,
    public date?:Date,
    public name?:string,
    public holidaytype?:Holidaytype|null|string,
    public weekday?:Weekday,
    ){}
}