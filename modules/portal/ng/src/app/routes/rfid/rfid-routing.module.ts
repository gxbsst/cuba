import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RfidIndexComponent} from './rfid-index/rfid-index.component';

const routes: Routes = [
    { path: 'rfid-index', component:  RfidIndexComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RfidRoutingModule { }
