import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RfidIndexComponent} from './rfid-index/rfid-index.component';
import {RfidDetailComponent} from './rfid-detail/rfid-detail.component';

const routes: Routes = [
    { path: 'rfid-index', component:  RfidIndexComponent},
    { path: 'rfid-detail', component:  RfidDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RfidRoutingModule { }
