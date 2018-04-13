import {AfterViewInit, Component, OnInit} from '@angular/core';
import {SimpleTableColumn} from '@delon/abc';
import {OpcService} from '../../../service/OpcService';
import {StorageService} from '../../../service/StorageService';
import {Router} from '@angular/router';

@Component({
    selector: 'app-opc-index',
    templateUrl: './opc-index.component.html',
})
export class OpcIndexComponent implements OnInit, AfterViewInit {

    data = [];

    columns: SimpleTableColumn[] = [
        {title: 'itemId', render: 'custom'},
        {title: '值', index: 'value'},
        {title: '描述', index: 'description'},
    ];

    constructor(private router: Router,
                private opcService: OpcService,
                private storageService: StorageService) {
    }

    ngOnInit() {

    }

    ngAfterViewInit() {
        this.opcService.fetch().then(response => {
            this.data = response;
        }, err => {

        });
    }

    goToDetail(item: any) {
        this.storageService.setData(item);
        this.router.navigate(['/opc/opc-detail']);
    }

}
