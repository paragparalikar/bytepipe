import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app.routes';
import { authConfig } from './auth/auth.config';
import { tenantHeaderInterceptor } from './security/tenant.interceptor';
import { AbstractSecurityStorage, DefaultLocalStorageService, provideAuth, authInterceptor } from 'angular-auth-oidc-client';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideHttpClient(withInterceptors([tenantHeaderInterceptor, authInterceptor()])),
    provideRouter(routes), 
    provideAuth(authConfig),
    {
      provide: AbstractSecurityStorage,
      useClass: DefaultLocalStorageService,
    },]
};
