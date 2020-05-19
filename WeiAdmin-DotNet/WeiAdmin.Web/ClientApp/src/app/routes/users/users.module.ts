import { NgModule } from '@angular/core';
import { SharedModule } from '@shared';
import { UsersRoutingModule } from './users-routing.module';
import { UsersUserComponent } from './user/user.component';
import { UsersUserViewComponent } from './user/view/view.component';

const COMPONENTS = [
  UsersUserComponent];
const COMPONENTS_NOROUNT = [
  UsersUserViewComponent];

@NgModule({
  imports: [
    SharedModule,
    UsersRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
})
export class UsersModule { }
