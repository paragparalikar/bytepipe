import { Routes } from '@angular/router';
import { LoginComponent } from './security/login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
    },
    {
        path: 'signin',
        component: LoginComponent
    },
    {path: 'home/dashboard', component: DashboardComponent},
    {path: 'connectors', component: DashboardComponent},
    {path: 'transformers', component: DashboardComponent},
    {path: 'data-flows', component: DashboardComponent},
    {path: 'reports', component: DashboardComponent},
    {path: 'alerts', component: DashboardComponent},
    {path: 'users', component: DashboardComponent},
    {path: 'user-groups', component: DashboardComponent},
    {path: 'roles', component: DashboardComponent},
    {path: 'audit', component: DashboardComponent}

];
