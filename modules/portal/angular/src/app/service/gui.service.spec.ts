import { TestBed, inject } from '@angular/core/testing';

import { GuiService } from './gui.service';

describe('GuiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GuiService]
    });
  });

  it('should be created', inject([GuiService], (service: GuiService) => {
    expect(service).toBeTruthy();
  }));
});
