import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OpcIndexComponent} from './opc-index/opc-index.component';
import {OpcLogsComponent} from './opc-logs/opc-logs.component';

const routes: Routes = [
    { path: 'opc-index', component:  OpcIndexComponent},
    {path: 'opc-logs', component: OpcLogsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OpcRoutingModule { }
