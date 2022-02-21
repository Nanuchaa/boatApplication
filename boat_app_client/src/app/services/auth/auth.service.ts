import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/class/user';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends ConfigService {

  displayInvalid: boolean = false;

  constructor(http: HttpClient, public router: Router) {
      super(http);
      this.initialize();
   }


   login(user: User){
     console.log("hte url is "+ this.authUrl);
     return this.http.post(this.authUrl, JSON.stringify(user), {
       headers: {'Content-Type': 'application/json'}
     })
     .toPromise()
     .then((jwt: any)=>{
       console.log("the token is ", jwt.jwttoken);
       localStorage.setItem("token", jwt.jwttoken)
       this.router.navigateByUrl("/boatlist");
     }).catch(err => {
       if(err.status===401){
        this.displayInvalid = true;
        console.error('error while logging in ', err);
       }
     })
   }

   logout(){
     localStorage.clear();
     this.router.navigateByUrl("/login");
   }
}
