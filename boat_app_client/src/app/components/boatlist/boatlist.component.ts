import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Boat } from 'src/app/class/boat';
import { AuthService } from 'src/app/services/auth/auth.service';
import { BoatService } from 'src/app/services/boat/boat.service';

@Component({
  selector: 'app-boatlist',
  templateUrl: './boatlist.component.html',
  styleUrls: ['./boatlist.component.css'], 
})
export class BoatlistComponent implements OnInit {

  boat: Boat = new Boat();
  displayAdd: boolean = false;
  displayEdit: boolean = false;
  displayDelete: boolean = false;

  message ="";
  
  constructor(public service: BoatService, public router: Router, public authService: AuthService) { 
   
  }

  ngOnInit(){   
  }

  showAddBoat() {
    this.message ="";
    this.boat = new Boat();
    this.displayAdd = true;
  }

  cancel() {
    this.displayAdd = false;
    this.displayEdit = false;
    this.displayDelete = false;
  }

  showEditBoat(item: any) {
    this.message ="";
    this.boat = Object.assign({}, item);    //No deepcopy
    this.displayEdit = true;
  }

  showDeleteBoat(item: any) {
    this.boat = item;
    this.displayDelete = true;
  }

  goToDetailsView(item: any) {
    this.boat = item;
    this.router.navigateByUrl("/boat/details/"+this.boat.id);
  }


  addBoat(boat: Boat){
    if(boat.name=="" || boat.description=="" || boat.price<=0){
      this.message="Please fill in the mandatory fields.";
    }else {
      this.service.saveBoat(boat);
      this.displayAdd = false;
    }
  }

  editBoat(boat: Boat){
    if(boat.name=="" || boat.description=="" || boat.price<=0){
      this.message="Please fill in the mandatory fields.";
    }else {
      this.service.saveBoat(boat);
      this.displayEdit = false;
    }
  }

  deleteBoat(boat: any){
    this.service.deleteBoat(boat.id);
    this.displayDelete = false;
  }

}
