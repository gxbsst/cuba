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
    groupData= [];
    searchValue = '';

    columns: SimpleTableColumn[] = [
        {title: '时间', index: 'createTs'},
        {title: '主题', render: 'custom'},
        {title: '信息', index: 'message'},
    ];

    groupColumns: SimpleTableColumn[] = [
        {title: '主题', index: 'topic'},
        {title: '统计', index: 'count'}
    ];

    constructor(private mqttService: MqttService) {

    }

    ngOnInit() {
        this.fetch();
        this.queryGroupBy();

        const timer = Observable.timer(5000, AppSettings.TIMER_PERIOD);
        timer.subscribe(t => {
            this.fetch();
        });
    }

    search() {
        if (this.searchValue === '') {
            this.fetch();
        } else {
            this.query();
        }
    }

    fetch() {
        this.mqttService.fetch().then(response => {
            this.data = JSON.parse(response);
        }, err => {
        });
    }

    query() {
        this.mqttService.query(this.searchValue).then((response) => {

            this.data = JSON.parse(response);
        }, err => {

        });
    }

    queryGroupBy() {
        this.mqttService.groupByTopic().then((response) => {
            this.groupData = JSON.parse(response);
        }, err => {

        });
    }

}
