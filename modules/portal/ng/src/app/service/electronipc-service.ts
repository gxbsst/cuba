import {Injectable} from '@angular/core';
import {ElectronService} from 'ngx-electron';

@Injectable()
export class ElectronIpcService {
    constructor(private electron: ElectronService) {
    }

    /**
     * Sends a list of addresses to the main process, to be
     * updated on the Locations menu.
     */
    updateLocationsMenu() {
        if (this.electron.ipcRenderer) {
            // this.electron.ipcRenderer.send('Locations', addresses, provisionedId);
            this.electron.ipcRenderer.send('Locations');
        }
    }

    // 启动APP
    installApp(appName: string) {
        if (this.electron.ipcRenderer) {
            this.electron.ipcRenderer.send('installApp', appName);
        }
    }

    // 关闭APP
    closeApp(appName: string) {
        if (this.electron.ipcRenderer) {
            this.electron.ipcRenderer.send('closeApp', appName);
        }
    }
}
