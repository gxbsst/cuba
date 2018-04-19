import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {KanbanListComponent} from './kanban-list/kanban-list.component';

const routes: Routes = [
    {path: 'kanban-list', component: KanbanListComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class KanbanRoutingModule {
}
