import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class GuardService implements CanActivate {
  
  constructor(public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot):boolean {
    if(route.queryParams==null) return false;

    var id = route.queryParams['token'];
    
    if (id==null) {
      id = localStorage.getItem('token');
    }
    if (id==null) {
      this.router.navigate(['/notfound'])
      return false;
    }
  
    localStorage.setItem('token', id);
    this.router.navigateByUrl("/login");
    return false;
  
  }
}
