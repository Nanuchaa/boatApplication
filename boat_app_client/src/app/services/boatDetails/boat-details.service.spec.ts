import { TestBed } from '@angular/core/testing';

import { BoatDetailsService } from './boat-details.service';

describe('BoatDetailsService', () => {
  let service: BoatDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoatDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
