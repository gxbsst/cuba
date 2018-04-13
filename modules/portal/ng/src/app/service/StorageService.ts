import {Injectable} from '@angular/core';

@Injectable()
export class StorageService {

    public data: object = {};

    constructor() {
    }

    public getData(): any {
        return this.data;
    }

    public setData(data: any): void {
        this.data = data;
    }
}
