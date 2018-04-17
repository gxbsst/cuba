import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { OpcRoutingModule } from './opc-routing.module';
import { OpcIndexComponent } from './opc-index/opc-index.component';
import { OpcLogsComponent } from './opc-logs/opc-logs.component';

const COMPONENT_NOROUNT = [];

@NgModule({
  imports: [
    SharedModule,
    OpcRoutingModule
  ],
  declarations: [
      ...COMPONENT_NOROUNT,
      OpcIndexComponent,
      OpcLogsComponent
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class OpcModule { }
