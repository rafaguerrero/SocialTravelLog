import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {TempDirective} from './directives/temp.directive';

@NgModule({
    declarations: [
        TempDirective
    ],
    imports: [
        CommonModule
    ]
})
export class AppModule {
}
