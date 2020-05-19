import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersUserComponent } from './user/user.component';

const routes: Routes = [

  { path: 'user', component: UsersUserComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
