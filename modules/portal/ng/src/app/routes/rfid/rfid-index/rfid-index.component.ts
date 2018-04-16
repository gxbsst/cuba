import {Component, OnInit} from '@angular/core';
import {SimpleTableColumn} from '@delon/abc';
import {CubaApp} from '@cuba-platform/rest/dist-node/cuba';
import {Router} from '@angular/router';
import {StorageService} from '../../../service/StorageService';
import {RfidService} from '../../../service/RfidService';
import {Observable} from 'rxjs/Rx';
import {AppSettings} from '@core/AppSettings';

@Component({
    selector: 'app-rfid-index',
    templateUrl: './rfid-index.component.html',
})
export class RfidIndexComponent implements OnInit {

    data = [];
    searchValue = '';

    columns: SimpleTableColumn[] = [
        {title: '名称', render: 'custom'},
        {title: 'IP', index: 'ip'},
        {title: 'SpecId', index: 'specId'},
        {
            title: '操作区',
            buttons: [
                {
                    text: '编辑',
                    type: 'none',
                }]
        }
    ];

    constructor(private router: Router,
                private rfidService: RfidService,
                public storageService: StorageService) {
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
            this.query();
        }
    }

    fetch() {
        this.rfidService.fetch().then(response => {
            this.data = JSON.parse(response);
        }, err => {
        });
    }

    query() {
        this.rfidService.query(this.searchValue).then((response) => {
            this.data = response;
        }, err => {

        });
    }

    goToDetail(item: any) {
        this.storageService.setData(item);
        this.router.navigate(['/rfid/rfid-detail']);
    }

}
