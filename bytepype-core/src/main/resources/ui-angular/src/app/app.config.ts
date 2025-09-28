import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { routes } from './app.routes';
import { authConfig } from './auth/auth.config';
import { AuthInterceptor } from './auth/auth-interceptor';
import { AbstractSecurityStorage, DefaultLocalStorageService, OidcSecurityService, provideAuth } from 'angular-auth-oidc-client';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideHttpClient(withInterceptorsFromDi()),
    provideRouter(routes, withComponentInputBinding()),
    provideAuth(authConfig),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
      deps: [OidcSecurityService]
    }, {
      provide: AbstractSecurityStorage,
      useClass: DefaultLocalStorageService,
    },]
};
