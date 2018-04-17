import {Injectable} from '@angular/core';
import {CubaApp} from '@cuba-platform/rest/dist-node/cuba';

@Injectable()
export class OpcService {
    cubaApp;

    constructor() {
        this.cubaApp = new CubaApp('myApp', 'http://localhost:8088/app/rest/');
    }

    fetch(): Promise<any> {
        return this.cubaApp.query('sct$VtOPCSignal', 'vtOPCSignal-query');
    }

    fetchItemLogs(vtOPCSignalId: String): Promise<any> {
        return this.cubaApp.query('sct$VtOPCSignalUpdateLog', 'vtOPCSignalUpdateLog-query', {'vtOPCSignalId': vtOPCSignalId});
    }

    fetchBuhlerBins(): Promise<any> {
        return this.cubaApp.query('sct$VtBuhlerBin', 'vtBuhlerBin-query');
    }
}
