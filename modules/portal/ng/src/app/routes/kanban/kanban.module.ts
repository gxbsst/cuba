import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { KanbanRoutingModule } from './kanban-routing.module';
import { KanbanListComponent } from './kanban-list/kanban-list.component';

const COMPONENT_NOROUNT = [];

@NgModule({
  imports: [
    SharedModule,
    KanbanRoutingModule
  ],
  declarations: [
      ...COMPONENT_NOROUNT,
      KanbanListComponent
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class KanbanModule { }
