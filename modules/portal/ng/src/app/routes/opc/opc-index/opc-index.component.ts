import {Component, OnInit} from '@angular/core';
import {SimpleTableColumn} from '@delon/abc';
import {OpcService} from '../../../service/OpcService';
import {StorageService} from '../../../service/StorageService';
import {Router} from '@angular/router';
import {_HttpClient} from '@delon/theme';
import {AppSettings} from '@core/AppSettings';
import {Observable} from 'rxjs/Rx';

@Component({
    selector: 'app-opc-index',
    templateUrl: './opc-index.component.html',
})
export class OpcIndexComponent implements OnInit {

    data = [];

    binData = [];


    columns: SimpleTableColumn[] = [
        {title: 'itemId', render: 'custom'},
        {title: '值', index: 'value'},
        {title: '描述', index: 'description'},
    ];


    percent = 87;

    constructor(private router: Router,
                private opcService: OpcService,
                private http: _HttpClient,
                private storageService: StorageService) {

    }

    ngOnInit() {
        this.fetch();
        this.fetchBuhlerBins();

        const timer = Observable.timer(5000, AppSettings.TIMER_PERIOD);
        timer.subscribe(t => {
            this.fetchBuhlerBins();
        });
    }


    fetch() {
        this.opcService.fetch().then(response => {
            this.data = response;
        }, err => {

        });
    }

    fetchBuhlerBins() {
        this.opcService.fetchBuhlerBins().then(response => {
            this.binData = response;
        }, err => {

        });
    }

    goToDetail(item: any) {
        this.storageService.setData(item);
        this.router.navigate(['/opc/opc-logs']);
    }

}
