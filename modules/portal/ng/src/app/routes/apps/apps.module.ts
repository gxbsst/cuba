import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { AppsRoutingModule } from './apps-routing.module';
import { AppsListComponent } from './apps-list/apps-list.component';

const COMPONENT_NOROUNT = [];

@NgModule({
  imports: [
    SharedModule,
    AppsRoutingModule
  ],
  declarations: [
      ...COMPONENT_NOROUNT,
      AppsListComponent
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class AppsModule { }
