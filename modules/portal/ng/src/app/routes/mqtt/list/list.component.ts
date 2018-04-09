import {Component, OnInit} from '@angular/core';
import {CubaApp} from '@cuba-platform/rest/dist-node/cuba';
import {SimpleTableColumn} from '@delon/abc';

@Component({
    selector: 'app-list',
    templateUrl: './list.component.html',
})
export class ListComponent implements OnInit {

    data = [];
    cubaApp: any;
    searchValue = '';

    columns: SimpleTableColumn[] = [
        { title: '时间', index: 'createTs' },
        { title: '主题', render: 'custom' },
        { title: '信息', index: 'message' },
    ];

    constructor() {
        this.cubaApp = new CubaApp('myApp', 'http://localhost:8088/app/rest/');
    }

    ngOnInit() {
        this.fetch();
    }

    search() {
        if (this.searchValue === '') {
            this.fetch();
        } else {
            this.query();
        }
    }

    fetch() {
        this.cubaApp.invokeService('cuba_MqttService', 'mqtt').then(response => {
            this.data = JSON.parse(response);
        }, err => {
        });
    }

    query() {
        this.cubaApp.query('cuba$Mqtt', 'mqtt-query', {topic: this.searchValue}).then((response) => {
            this.data = response;
        }, err => {

        });
    }

}
