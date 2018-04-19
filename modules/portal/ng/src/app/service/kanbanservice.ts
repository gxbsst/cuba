import {Injectable} from '@angular/core';
import {CubaApp} from '@cuba-platform/rest/dist-node/cuba';

@Injectable()
export class KanbanService {

    cubaApp;

    constructor() {
        this.cubaApp = new CubaApp('myApp', 'http://localhost:8088/app/rest/');
    }


    fetch(): Promise<any> {
        return this.cubaApp.invokeService('sct_VtClientDeviceInformationService', 'query');
    }

}
