import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Boat } from 'src/app/class/boat';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  config = "assets/config.json";
  backendurl = "";
  authUrl = "";

  getBoatUrl = "";
  getBoatlistUrl = "";
  saveBoatUrl = "";
  deleteBoatUrl = "";

  getBoatDetailsUrl = "";
  saveBoatDetailsUrl = "";
  deleteBoatDetailsUrl = "";

  selectedBoat=new Boat();


  constructor(public http: HttpClient) {
    console.log("cofnig service called");
    //this.initialize();
  }

  initialize() {
    return this.http.get(this.config)
      .toPromise()
      .then((conf: any) => {
        this.backendurl = conf.backend_server;
        this.authUrl = this.backendurl + conf.authenticate;

        //Boat Urls
        this.getBoatlistUrl = this.backendurl + conf.get_boatlist;
        this.getBoatUrl = this.backendurl + conf.get_boat;
        this.saveBoatUrl = this.backendurl + conf.save_boat;
        this.deleteBoatUrl = this.backendurl + conf.delete_boat;

        //BoatDetails Urls
        this.getBoatDetailsUrl = this.backendurl + conf.get_boatdetails_byboatidfk;
        this.saveBoatDetailsUrl = this.backendurl + conf.save_boatdetails;
        this.deleteBoatDetailsUrl = this.backendurl + conf.delete_boatdetails;
      })
  }


  getHeaders(){
    const token = 'Bearer '+localStorage.getItem('token');
    
    let httpOptions = {
    headers: new HttpHeaders({ 
      //"Authorization": localStorage.getItem('token'),
      'Content-Type': 'application/json',
      'Authorization': token
    })}
    return httpOptions;
  };


}
