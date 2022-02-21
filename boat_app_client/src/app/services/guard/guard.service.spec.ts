import { ActivatedRouteSnapshot } from '@angular/router';

import { GuardService } from './guard.service';

xdescribe('GuardService', () => {
  let service: GuardService;
  const routerMock = jasmine.createSpyObj('Router', ['navigate']);
  beforeEach(() => {
    service = new GuardService(routerMock);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  xdescribe('canActivate guard service', () => {
    it('should return True when param contains token', () => {
      let routerSnapshot = new ActivatedRouteSnapshot();
      routerSnapshot.queryParams={token:'testtoken'};
      const response = service.canActivate(routerSnapshot);
      expect(response).toBeTrue();
    });

    it('should return false when param contains no token', () => {
      let routerSnapshot = new ActivatedRouteSnapshot();
      routerSnapshot.queryParams = null;
      let response = service.canActivate(routerSnapshot);
      expect(response).toBeFalse();
    });

    it('should return false when token is null and localStorage contains no token', () => {
      let routerSnapshot = new ActivatedRouteSnapshot();
      routerSnapshot.queryParams={};
      routerSnapshot.queryParams.token = null;
      localStorage.clear();
      let response = service.canActivate(routerSnapshot);
      expect(response).toBeFalse();
    });
  });


});
