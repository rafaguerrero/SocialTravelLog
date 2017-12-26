import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { TempDirective } from './app/directives/temp.directive';

@NgModule({
  declarations: [
      TempDirective
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: []
})

export class AppModule { }
