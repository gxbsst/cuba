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

    status = [
        {index: 0, text: '关闭', value: false, type: 'default', checked: false},
        {index: 1, text: '运行中', value: false, type: 'processing', checked: false},
    ];
    columns: SimpleTableColumn[] = [
        {title: '应用名', index: 'name'},
        {
            title: '状态',
            index: 'status',
            render: 'status',
            filters: this.status,
            filter: () => true
        },
        {
            title: '操作区',
            buttons: [
                {
                    text: '启动',
                    type: 'none',
                    click: (record: any) => this.install(record.name),
                }
            ]
        }
    ];
    data = [
        {
            key: '1',
            name: 'OPC',
            statusType: 'default',
            statusText: '关闭'
        }, {
            key: '2',
            name: 'SCT',
            statusType: 'default',
            statusText: '关闭'

        }, {
            key: '3',
            name: 'RFID',
            statusType: 'default',
            statusText: '关闭'
        },
        {
            key: '4',
            name: 'PRINTER',
            statusType: 'default',
            statusText: '关闭'
        },
        {
            key: '5',
            name: 'CUBA',
            statusType: 'default',
            statusText: '关闭'
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
                debugger
                if (status === 'closed') {
                    this.data[0]['statusType'] = 'default';
                    this.data[0]['statusText'] = '关闭';
                } else {
                    this.data[0]['statusType'] = 'processing';
                    this.data[0]['statusText'] = '运行中';
                }
            });

            this.electron.ipcRenderer.on('appSctStatus', (event, status) => {
                if (status === 'closed') {
                    this.data[1]['statusType'] = 'default';
                    this.data[1]['statusText'] = '关闭';
                } else {
                    this.data[1]['statusType'] = 'processing';
                    this.data[1]['statusText'] = '运行中';
                }
            });
            this.electron.ipcRenderer.on('appRFIDStatus', (event, status) => {
                if (status === 'closed') {
                    this.data[2]['statusType'] = 'default';
                    this.data[2]['statusText'] = '关闭';
                } else {
                    this.data[2]['statusType'] = 'processing';
                    this.data[2]['statusText'] = '运行中';
                }
            });

            this.electron.ipcRenderer.on('appPrinterStatus', (event, status) => {
                if (status === 'closed') {
                    this.data[3]['statusType'] = 'default';
                    this.data[3]['statusText'] = '关闭';
                } else {
                    this.data[3]['statusType'] = 'processing';
                    this.data[3]['statusText'] = '运行中';
                }
            });
            this.electron.ipcRenderer.on('appCUBAStatus', (event, status) => {
                if (status === 'closed') {
                    this.data[4]['statusType'] = 'default';
                    this.data[4]['statusText'] = '关闭';
                } else {
                    this.data[4]['statusType'] = 'processing';
                    this.data[4]['statusText'] = '运行中';
                }
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
