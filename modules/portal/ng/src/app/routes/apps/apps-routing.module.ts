import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppsListComponent} from './apps-list/apps-list.component';

const routes: Routes = [
    { path: 'apps-list', component: AppsListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppsRoutingModule { }
