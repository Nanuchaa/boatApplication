import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BoatDetails } from 'src/app/class/boatdetails';
import { ConfigService } from '../config/config.service';

@Injectable({
  providedIn: 'root'
})
export class BoatDetailsService {

  boatDetails = new BoatDetails();

  constructor(public http: HttpClient, public confservice: ConfigService) {
    
  }


  getBoatDetailsByBoatIdfk(boatIdfk: number){
    this.http.get<BoatDetails>(this.confservice.getBoatDetailsUrl + boatIdfk, this.confservice.getHeaders())
    .toPromise()
    .then((details:BoatDetails) => {
      if(details){
        this.boatDetails = details;
        this.boatDetails.manufacturedDate=new Date(details.manufacturedDate);
        //console.log("the boatdetails is ", this.boatDetails);
      } else {
        this.boatDetails = new BoatDetails();
      }
    }).catch(err => {
      console.warn('Error while getting Boatdetails by BoatIdfk ', err);
    });
  }

  saveBoatDetails() {
    this.http.post(this.confservice.saveBoatDetailsUrl, this.boatDetails, this.confservice.getHeaders())
    .toPromise()
    .then((_details) => {
      this.getBoatDetailsByBoatIdfk(this.boatDetails.boatIdfk);
    }).catch(err => {
      console.warn('Error while saving boatDetails ', err);
    })
  }

  deleteBoatDetails(id: number) {
    this.http.delete(this.confservice.deleteBoatDetailsUrl + id, this.confservice.getHeaders())
    .toPromise()
    .then((_details) => {
      this.getBoatDetailsByBoatIdfk(this.boatDetails.boatIdfk);
    }).catch(err => {
      console.warn('Error while deleting boatDetails ', err);
    });
  }

}
