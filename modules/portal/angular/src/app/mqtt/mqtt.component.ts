import {Component, NgModule, OnInit} from '@angular/core';
import {CubaApp} from "@cuba-platform/rest/dist-node/cuba";


@Component({
  selector: 'app-mqtt',
  templateUrl: './mqtt.component.html',
  styleUrls: ['./mqtt.component.scss']
})
export class MqttComponent implements OnInit {
  data = [];
  cubaApp: any;
  searchValue = '';

  constructor() {
    this.cubaApp = new CubaApp('myApp', 'http://localhost:8088/app/rest/')
  }

  ngOnInit() {
    this.fetch()
  }

  search() {
    if(this.searchValue == "") {
      this.fetch()
    } else {
      this.query()
    }
  }

  fetch() {
    this.cubaApp.invokeService("cuba_MqttService", "mqtt").then(response => {
      this.data = JSON.parse(response)
    }, err => {
    })
  }

  query() {
    this.cubaApp.query("cuba$Mqtt", "mqtt-query", {topic: this.searchValue}).then((response) => {
      this.data = response
    }, err => {

    })
  }


}
