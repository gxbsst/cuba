import {Component, OnInit} from '@angular/core';
import {_HttpClient} from '@delon/theme';
import {SimpleTableColumn} from '@delon/abc';
import {CommonService} from '../../../service/CommonService';
import {ElectronIpcService} from '../../../service/electronipc-service';
import {ElectronService} from 'ngx-electron';

@Component({
    selector: 'app-apps-list',
    templateUrl: './apps-list.component.html',
})
export class AppsListComponent implements OnInit {

    data = [
        {
            key: '1',
            name: 'OPC',
            status: 'closed',

        }, {
            key: '2',
            name: 'SCT',
            status: 'closed',

        }, {
            key: '3',
            name: 'RFID',
            status: 'closed',
        },
        {
            key: '4',
            name: 'PRINTER',
            status: 'closed',
        },
        {
            key: '5',
            name: 'CUBA',
            status: 'closed',
        }
    ];

    columns: SimpleTableColumn[] = [
        {title: '应用名', index: 'name'},
        {title: '状态', index: 'status'},
        {
            title: '操作区',
            buttons: [
                {
                    text: '启动',
                    type: 'none',
                    click: (record: any) => this.install(record.name),
                },
                {
                    text: '<a target="_blank" href="http://www.red.com">预览</a>',
                    type: 'none',
                }

            ]
        }
    ];

    constructor(private commonService: CommonService,
                public service: ElectronIpcService,
                private electron: ElectronService,
                private http: _HttpClient) {
    }

    ngOnInit() {
        if (this.electron.ipcRenderer) {
            // callback style
            // si.cpu(function (data) {
            //     console.log('CPU-Information:');
            //     console.log(data);
            // });

            // promises style - new in version 3
            // si.cpu()
            //     .then(data => console.log(data))
            //     .catch(error => console.error(error));

            this.electron.ipcRenderer.on('appOPCStatus', (event, status) => {
                this.data[0]['status'] = status;
            });

            this.electron.ipcRenderer.on('appSctStatus', (event, status) => {
                this.data[1]['status'] = status;
            });
            this.electron.ipcRenderer.on('appRFIDStatus', (event, status) => {
                this.data[2]['status'] = status;
            });

            this.electron.ipcRenderer.on('appPrinterStatus', (event, status) => {
                this.data[3]['status'] = status;
            });
        }
    }

    install(name: string) {
        this.commonService.startApp(name, (status) => {
            // this.data[1]['status'] = status;
        });
    }

    closeApp(name: string) {
        this.service.closeApp(name);
    }


}
