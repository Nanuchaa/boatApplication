import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Boat, TestBoatList } from 'src/app/class/boat';
import { ConfigService } from '../config/config.service';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  boatlist: Boat[];
  boat = new Boat();

  constructor(public http: HttpClient, public confservice: ConfigService, public authService: AuthService) { 
    
    this.boatlist = [];
    this.confservice.initialize().then(() => {
      this.getBoatlist();
    })
  }


  getBoatlist() {  
    this.http.get(this.confservice.getBoatlistUrl, this.confservice.getHeaders()).subscribe(
      (list: any) => {
        this.boatlist = list;
      },
      (err: Error) => {
        console.log('Error occurred while getting Boatlist.', err);
        this.authService.logout();
      }) 
  }

  saveBoat(boat: Boat) {
    this.http.post(this.confservice.saveBoatUrl, boat, this.confservice.getHeaders())
    .toPromise()
    .then((_boat) => {
      this.getBoatlist();
    }).catch(err => {
      console.warn('Error while saving boat ', err);
    })
  }

  getBoatById(id: number) {
    return this.http.get(this.confservice.getBoatUrl + id, this.confservice.getHeaders())
    .toPromise().catch(err => {
      console.warn('Error while getting boatById ', err);
    });
  }

  deleteBoat(id: number) {
    this.http.delete(this.confservice.deleteBoatUrl + id, this.confservice.getHeaders()).toPromise()
    .then((_boat) => {
      this.getBoatlist();
    }).catch(err => {
      console.warn('Error while deleting boat ', err);
    })
  }


}


