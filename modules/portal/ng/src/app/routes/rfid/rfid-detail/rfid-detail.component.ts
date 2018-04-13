import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SimpleTableColumn} from '@delon/abc';
import {StorageService} from '../../../service/StorageService';
import {CubaApp} from '@cuba-platform/rest/dist-node/cuba';
import {MqttService} from '../../../service/MqttService';

@Component({
    selector: 'app-rfid-detail',
    templateUrl: './rfid-detail.component.html',
})
export class RfidDetailComponent implements OnInit {
    item;
    cubaApp;
    rfidPortsData: any[];

    rfidPortsColumns: SimpleTableColumn[] = [
        {title: '名称', index: 'name'},
        {title: '天线识别号', index: 'antenna'},
        {title: '信号强度', index: 'valid_rssi'},
        {title: 'Mqtt主题', index: 'mqtt_topics'},
    ];

    mqttData: any[];

    mqttColumns: SimpleTableColumn[] = [
        {title: '时间', index: 'createTs'},
        {title: '主题', index: 'topic'},
        {title: '信息', index: 'message'},
    ];


    constructor(private route: ActivatedRoute,
                private router: Router,
                private storageService: StorageService,
                private mqttService: MqttService) {
        this.cubaApp = new CubaApp('myApp', 'http://localhost:8088/app/rest/');
    }

    ngOnInit() {
        this.item = this.storageService.getData();
        this.rfidPortsData = this.item['port'];
        this.mqttService.query(this.rfidPortsData[0].mqtt_topics).then(response => {
            this.mqttData = JSON.parse(response);
        }, err => {

        });
    }

}
