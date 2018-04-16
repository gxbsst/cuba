import {Injectable} from '@angular/core';
import {CubaApp} from '@cuba-platform/rest/dist-node/cuba';

@Injectable()
export class RfidService {
    cubaApp;
    data;
    constructor() {
        this.cubaApp = new CubaApp('myApp', 'http://localhost:8088/app/rest/');
    }

    query(queryString): Promise<any> {
        return this.cubaApp.query('sct$Mqtt', 'mqtt-query', {topic: queryString});
    }

    fetch() {
        return this.cubaApp.invokeService('sct_RfidService', 'rfid');
    }

}
