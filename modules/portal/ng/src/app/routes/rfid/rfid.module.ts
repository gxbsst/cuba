import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { RfidRoutingModule } from './rfid-routing.module';
import {RfidIndexComponent} from './rfid-index/rfid-index.component';

const COMPONENT_NOROUNT = [];

@NgModule({
  imports: [
    SharedModule,
    RfidRoutingModule
  ],
  declarations: [
      ...COMPONENT_NOROUNT,
      RfidIndexComponent
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class RfidModule { }
