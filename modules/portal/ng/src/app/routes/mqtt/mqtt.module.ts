import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { MqttRoutingModule } from './mqtt-routing.module';
import { ListComponent } from './list/list.component';

const COMPONENT_NOROUNT = [];

@NgModule({
  imports: [
    SharedModule,
    MqttRoutingModule
  ],
  declarations: [
      ...COMPONENT_NOROUNT,
      ListComponent
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class MqttModule { }
