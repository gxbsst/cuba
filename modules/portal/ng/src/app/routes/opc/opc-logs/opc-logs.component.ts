import {Component, OnInit} from '@angular/core';
import {StorageService} from '../../../service/StorageService';
import {SimpleTableColumn} from '@delon/abc';
import {OpcService} from '../../../service/OpcService';
import {AppSettings} from '@core/AppSettings';
import {Observable} from 'rxjs/Rx';

@Component({
    selector: 'app-opc-logs',
    templateUrl: './opc-logs.component.html',
})
export class OpcLogsComponent implements OnInit {
    logsColumns: SimpleTableColumn[] = [
        {title: '值', index: 'value'},
        {title: '时间', index: 'updateTs'},
    ];

    logsData = [];

    item;

    constructor(private storageService: StorageService,
                private opcService: OpcService) {

        this.item = this.storageService.getData();

    }

    ngOnInit() {
        this.fetch();
        const timer = Observable.timer(5000, AppSettings.TIMER_PERIOD);
        timer.subscribe(t => {
            this.fetch();
        });
    }

    fetch() {
        this.opcService.fetchItemLogs(this.item.id).then(response => {
            this.logsData = response;
        }, err => {

        });
    }

}
