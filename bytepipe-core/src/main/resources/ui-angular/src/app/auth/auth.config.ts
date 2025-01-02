import { LogLevel, PassedInitialConfig } from 'angular-auth-oidc-client';

export const authConfig: PassedInitialConfig = {
  config: [{
            configId: 'google',
            authority: 'https://accounts.google.com',
            //https://accounts.google.com/.well-known/openid-configuration
            redirectUrl: window.location.origin,
            postLogoutRedirectUri: window.location.origin,
            clientId: '309470803706-erf2diogu09ah4eapebn6ianedf8gqdr.apps.googleusercontent.com',
            scope: 'openid profile email',
            responseType: 'id_token token',
            logLevel: LogLevel.Debug,
            silentRenew: true,
            autoUserInfo: true,
            useRefreshToken: true,
            ignoreNonceAfterRefresh: true,
            allowUnsafeReuseRefreshToken: true,
            triggerRefreshWhenIdTokenExpired: false,
            silentRenewUrl: window.location.origin + '/silent-renew.html',
            triggerAuthorizationResultEvent: true,
            checkRedirectUrlWhenCheckingIfIsCallback: false,
            renewTimeBeforeTokenExpiresInSeconds: 10,
            secureRoutes: ['http://localhost:8080']
          },
          {
            configId: 'microsoft',
            authority: 'https://login.microsoftonline.com/5d6c98ab-f906-4b12-9d24-ee1c69f44321/v2.0',
            //authWellknownEndpointUrl: 'https://login.microsoftonline.com/common/.well-known/openid-configuration',
            //https://login.microsoftonline.com/common/.well-known/openid-configuration
            //https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration
            redirectUrl: window.location.origin,
            postLogoutRedirectUri: window.location.origin,
            clientId: 'cd1b1632-df2b-42bc-8649-d32e008b3fa5',
            scope: 'openid profile email offline_access',
            responseType: 'code',
            logLevel: LogLevel.Debug,
            silentRenew: true,
            autoUserInfo: false,
            useRefreshToken: true,
            issValidationOff: true,
            ignoreNonceAfterRefresh: true,
            allowUnsafeReuseRefreshToken: true,
            triggerRefreshWhenIdTokenExpired: false,
            disableIdTokenValidation: true,
            checkRedirectUrlWhenCheckingIfIsCallback: true,
            silentRenewUrl: window.location.origin + '/silent-renew.html',
            renewTimeBeforeTokenExpiresInSeconds: 10,
            secureRoutes: ['http://localhost:8080']
          }]
}
