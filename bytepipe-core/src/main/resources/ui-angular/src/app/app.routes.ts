import { Routes } from '@angular/router';
import { LoginComponent } from './security/login/login.component';
import { RegisterationComponent } from './security/registeration/registeration.component';
import { ForgotPasswordComponent } from './security/forgot-password/forgot-password.component';

export const routes: Routes = [
    {
        path: 'signin',
        component: LoginComponent
    },
    {
        path: 'signup',
        component: RegisterationComponent
    },
    {
        path: 'forgot-password',
        component: ForgotPasswordComponent
    }


];
