import { Routes } from '@angular/router';
import { LoginComponent } from './security/login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { ConnectorComponent } from './connector/connector.component';
import { DataFlowComponent } from './data-flow/data-flow.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        children: [
            {path: 'dashboard', component: DashboardComponent},
            {path: 'connectors', component: ConnectorComponent},
            {path: 'transformers', component: DashboardComponent},
            {path: 'data-flows', component: DataFlowComponent},
            {path: 'reports', component: DashboardComponent},
            {path: 'alerts', component: DashboardComponent},
            {path: 'users', component: DashboardComponent},
            {path: 'user-groups', component: DashboardComponent},
            {path: 'roles', component: DashboardComponent},
            {path: 'audit', component: DashboardComponent}
        ]
    },
    {
        path: 'signin',
        component: LoginComponent
    }
];
