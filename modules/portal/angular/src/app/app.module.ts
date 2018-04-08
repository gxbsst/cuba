import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {RfidComponent} from './rfid/rfid.component';
import {SettingsComponent} from './settings/settings.component';
import {DevicesComponent} from './devices/devices.component';
import {AppsComponent} from './apps/apps.component';
import {RouterModule, Routes} from "@angular/router";
import {MqttComponent} from "./mqtt/mqtt.component";
import {NgZorroAntdModule} from "ng-zorro-antd";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

const appRoutes: Routes = [
  {path: 'apps', component: AppsComponent},
  {path: 'rfid', component: RfidComponent},
  {path: 'devices', component: DevicesComponent},
  {path: 'mqtt', component: MqttComponent},
  {path: 'settings', component: SettingsComponent},
  {path: '', redirectTo: '/apps', pathMatch: 'full'},
];

@NgModule({
  declarations: [
    AppComponent,
    RfidComponent,
    SettingsComponent,
    DevicesComponent,
    AppsComponent,
    MqttComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    NgZorroAntdModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    )
  ],
  providers: [],
  exports: [
    AppComponent,
    RfidComponent,
    SettingsComponent,
    DevicesComponent,
    AppsComponent,
    MqttComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
