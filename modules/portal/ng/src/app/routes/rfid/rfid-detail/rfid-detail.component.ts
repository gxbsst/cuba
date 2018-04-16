import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SimpleTableColumn} from '@delon/abc';
import {StorageService} from '../../../service/StorageService';
import {MqttService} from '../../../service/MqttService';
import {Observable} from 'rxjs/Rx';
import {AppSettings} from '@core/AppSettings';

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
    }

    ngOnInit() {
        this.item = this.storageService.getData();
        this.rfidPortsData = this.item['port'];
        this.query();

        const timer = Observable.timer(5000, AppSettings.TIMER_PERIOD);
        timer.subscribe(t => {
            this.query();
        });
    }

    private query() {
        this.mqttService.query(this.rfidPortsData[0].mqtt_topics).then(response => {
            this.mqttData = JSON.parse(response);
        }, err => {

        });
    }
}
