import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BoatDetails } from 'src/app/class/boatdetails';
import { BoatDetailsService } from 'src/app/services/boatDetails/boat-details.service';

@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.css']
})
export class BoatDetailsComponent {

  displayAdd: boolean = false;
  displayEdit: boolean = false;
  displayDelete: boolean = false;
  boatId = 0;
  message ="";

  constructor(public service: BoatDetailsService, private route: ActivatedRoute) {
    this.boatId = this.route.snapshot.params['id'];

    this.service.confservice.initialize().then(() => {
      this.service.getBoatDetailsByBoatIdfk(this.boatId);
    })
  }


  cancel() {
    this.displayAdd = false;
    this.displayEdit = false;
    this.displayDelete = false;
  }

  showAddBoatDetails() {
    this.message ="";
    this.service.boatDetails = new BoatDetails();
    this.service.boatDetails.boatIdfk = this.boatId;
    this.displayAdd = true;
  }

  showEditBoatDetails() {
    this.message ="";
    this.displayEdit = true;
  }

  showDeleteBoatDetails() {
    this.displayDelete = true;
  }


  saveBoatDetails() {
    if(this.service.boatDetails.weight<=0 || this.service.boatDetails.manufacturedDate==null){
      this.message="Please fill in the mandatory fields.";
    }else {
      this.service.saveBoatDetails();
      this.displayEdit = false;
    }
  }

  addBoatDetails() {
    if(this.service.boatDetails.weight<=0 || this.service.boatDetails.manufacturedDate==null){
      this.message="Please fill in the mandatory fields.";
    }else {
      this.service.saveBoatDetails();
      this.displayAdd = false;
      console.log('add details with boatIdfk: ', this.service.boatDetails.boatIdfk);
    }
  }

  deleteBoatDetails() {
    this.service.deleteBoatDetails(this.service.boatDetails.id);
    this.displayDelete = false;
  }

}
