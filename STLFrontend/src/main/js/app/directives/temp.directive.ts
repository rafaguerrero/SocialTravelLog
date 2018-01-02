import {
    Directive,
    ElementRef
} from '@angular/core';

@Directive({
    selector:".tempDirective"
})
export class TempDirective {
    constructor() {
        debugger;
        console.log("------------------------");
        console.log("Temp Directive");
        console.log("------------------------");
    }
}