import {Component, OnInit} from '@angular/core';
import {SimpleTableColumn} from '@delon/abc';
import {Observable} from 'rxjs/Rx';
import {MqttService} from '../../../service/MqttService';
import {AppSettings} from '@core/AppSettings';

@Component({
    selector: 'app-list',
    templateUrl: './list.component.html',
})
export class ListComponent implements OnInit {

    data = [];
    cubaApp: any;
    searchValue = '';

    columns: SimpleTableColumn[] = [
        {title: '时间', index: 'createTs'},
        {title: '主题', render: 'custom'},
        {title: '信息', index: 'message'},
    ];

    constructor(private mqttService: MqttService) {

    }

    ngOnInit() {
        this.fetch();

        const timer = Observable.timer(5000, AppSettings.TIMER_PERIOD);
        timer.subscribe(t => {
            this.fetch();
        });
    }

    search() {
        if (this.searchValue === '') {
            this.fetch();
        } else {
            this.query(this.searchValue);
        }
    }

    fetch() {
        this.mqttService.fetch().then(response => {
            this.data = JSON.parse(response);
        }, err => {
        });
    }

    query(searchValue) {
        this.mqttService.query(searchValue).then((response) => {
            this.data = response;
        }, err => {

        });
    }

}
