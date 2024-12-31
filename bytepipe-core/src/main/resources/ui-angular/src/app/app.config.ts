import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app.routes';
import { authConfig } from './auth/auth.config';
import { AbstractSecurityStorage, DefaultLocalStorageService, provideAuth } from 'angular-auth-oidc-client';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideHttpClient(),
    provideRouter(routes), 
    provideAuth(authConfig),
    {
      provide: AbstractSecurityStorage,
      useClass: DefaultLocalStorageService,
    },]
};
