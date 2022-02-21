import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoatDetailsComponent } from './components/boat-details/boat-details.component';
import { BoatlistComponent } from './components/boatlist/boatlist.component';
import { LoginComponent } from './components/login/login.component';
import { GuardService } from './services/guard/guard.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: "login", pathMatch: "full"},
  { path: 'id', canActivate:[GuardService], component: LoginComponent},
  { path: 'boatlist', component: BoatlistComponent},
  { path: 'boat/details/:id', component: BoatDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: false,
    useHash: true,
  }),],
  exports: [RouterModule]
})
export class AppRoutingModule { }
