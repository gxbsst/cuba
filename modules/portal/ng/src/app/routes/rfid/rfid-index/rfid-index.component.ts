import {Component, OnInit} from '@angular/core';
import {SimpleTableColumn} from '@delon/abc';
import {CubaApp} from '@cuba-platform/rest/dist-node/cuba';
import {Router} from '@angular/router';
import {StorageService} from '../../../service/StorageService';

@Component({
    selector: 'app-rfid-index',
    templateUrl: './rfid-index.component.html',
})
export class RfidIndexComponent implements OnInit {

    data = [];
    cubaApp: any;
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
                public storageService: StorageService) {
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
        this.cubaApp.invokeService('sct_RfidService', 'rfid').then(response => {
            this.data = JSON.parse(response);
        }, err => {
        });
    }

    query() {
        this.cubaApp.query('sct$Mqtt', 'mqtt-query', {topic: this.searchValue}).then((response) => {
            this.data = response;
        }, err => {

        });
    }

    goToDetail(item: any) {
        this.storageService.setData(item);
        this.router.navigate(['/rfid/rfid-detail']);
    }

}
