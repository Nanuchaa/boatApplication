import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http"; 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {AccordionModule} from 'primeng/accordion';  
import { FieldsetModule } from "primeng/fieldset";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { ToolbarModule } from "primeng/toolbar";
import { TooltipModule } from "primeng/tooltip";
import { TableModule } from "primeng/table";
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from "primeng/panel";
import { CheckboxModule } from "primeng/checkbox";
import {InputTextareaModule} from 'primeng/inputtextarea';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputNumberModule } from 'primeng/inputnumber';
import {CalendarModule} from 'primeng/calendar';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { BoatlistComponent } from './components/boatlist/boatlist.component';
import { BoatDetailsComponent } from './components/boat-details/boat-details.component';
import { ConfigService } from './services/config/config.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    BoatlistComponent,
    BoatDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    FieldsetModule,
    ButtonModule,
    AccordionModule,
    FieldsetModule,
    ButtonModule,
    RadioButtonModule,
    InputTextModule,
    ToolbarModule,
    TooltipModule,
    TableModule,
    DialogModule,
    PanelModule,
    CheckboxModule, 
    InputTextareaModule,
    InputNumberModule,
    CalendarModule
  ],
  providers: [ConfigService],
  bootstrap: [AppComponent]
})
export class AppModule { }
