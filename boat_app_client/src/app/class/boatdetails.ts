
export class BoatDetails {
 
    id: number=0;
    boatIdfk: number=0;
    weight: number=0;
    manufacturedDate: Date;
    quantity: number=0;
    type: string="";

    constructor(){
        this.manufacturedDate = new Date(Date.now());
    }

}


export const TestBoatDetails=[
    {id:1, boatIdfk:"1", weight: "100.5", manufacturedDate:"10.10.1990", quantity:"3", type: "type A"},
    {id:2, boatIdfk:"2", weight: "202", manufacturedDate:"02.10.1885", quantity:"1", type: "type B"},
    {id:3, boatIdfk:"4", weight: "150.5", manufacturedDate:"06.05.2000", quantity:"5", type: "type D"},
]