import { TestBed, inject } from '@angular/core/testing';

import { ElectronipcService } from './electronipc.service';

describe('ElectronipcService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ElectronipcService]
    });
  });

  it('should be created', inject([ElectronipcService], (service: ElectronipcService) => {
    expect(service).toBeTruthy();
  }));
});
