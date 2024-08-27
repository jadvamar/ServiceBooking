import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './company.component';
import { CompanyDashboadComponent } from './pages/company-dashboad/company-dashboad.component';


@NgModule({
  declarations: [
    CompanyComponent,
    CompanyDashboadComponent
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule
  ]
})
export class CompanyModule { }
