import {
    Directive,
    Renderer,
    ElementRef
} from '@angular/core';

@Directive({
    selector:".tempDirective"
})

class TempDirective {
    constructor(private el: ElementRef,
                private renderer: Renderer) {

        console.log("------------------------");
        console.log("Temp Directive");
        console.log("------------------------");
    }
}