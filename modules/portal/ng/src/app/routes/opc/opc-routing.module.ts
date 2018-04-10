import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OpcIndexComponent} from './opc-index/opc-index.component';

const routes: Routes = [
    { path: 'opc-index', component:  OpcIndexComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OpcRoutingModule { }
