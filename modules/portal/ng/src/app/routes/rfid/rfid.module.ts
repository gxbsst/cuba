import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { RfidRoutingModule } from './rfid-routing.module';
import {RfidIndexComponent} from './rfid-index/rfid-index.component';
import { RfidDetailComponent } from './rfid-detail/rfid-detail.component';

const COMPONENT_NOROUNT = [];

@NgModule({
  imports: [
    SharedModule,
    RfidRoutingModule
  ],
  declarations: [
      ...COMPONENT_NOROUNT,
      RfidIndexComponent,
      RfidDetailComponent
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class RfidModule { }
