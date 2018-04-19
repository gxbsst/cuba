import {Component, OnInit} from '@angular/core';
import {KanbanService} from '../../../service/kanbanservice';
import {SimpleTableColumn} from '@delon/abc';
import {AppSettings} from '@core/AppSettings';
import {Observable} from 'rxjs/Rx';

@Component({
    selector: 'app-kanban-list',
    templateUrl: './kanban-list.component.html',
})
export class KanbanListComponent implements OnInit {

    data = [];

    columns: SimpleTableColumn[] = [
        {title: '名称', index: 'name'},
        {title: 'IP', index: 'ip'},
        {title: '状态', render: 'status'},
        {title: '类型', index: 'type'},
        {title: 'LINE CODE', index: 'lineCode'},
        {title: 'CODE', index: 'code'},
    ];


    constructor(private service: KanbanService) {
    }

    ngOnInit() {
        this.fetch();
        this.fetch();

        const timer = Observable.timer(5000, AppSettings.TIMER_PERIOD);
        timer.subscribe(t => {
            this.fetch();
        });

    }

    fetch() {
        const self = this;

        this.service.fetch().then(response => {
            self.data = JSON.parse(response);
            self.data.forEach(function (item, index, theArray) {

                if (item.status === 'closed' || item.status === '') {
                    item['statusType'] = 'default';
                    item['statusText'] = '关闭';
                } else {
                    item['statusType'] = 'processing';
                    item['statusText'] = '运行中';
                }

                theArray[index] = item;
            });

        }, err => {

        });
    }
}
