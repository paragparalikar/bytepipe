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
            authority: 'https://login.microsoftonline.com/common',
            //https://login.microsoftonline.com/common/.well-known/openid-configuration
            redirectUrl: window.location.origin,
            postLogoutRedirectUri: window.location.origin,
            clientId: 'cd1b1632-df2b-42bc-8649-d32e008b3fa5',
            scope: 'openid profile email offline_access',
            responseType: 'code',
            logLevel: LogLevel.Debug,
            silentRenew: true,
            autoUserInfo: false,
            useRefreshToken: true,
            ignoreNonceAfterRefresh: true,
            allowUnsafeReuseRefreshToken: true,
            triggerRefreshWhenIdTokenExpired: false,
            disableIdTokenValidation: true,
            checkRedirectUrlWhenCheckingIfIsCallback: true,
            silentRenewUrl: window.location.origin + '/silent-renew.html',
            renewTimeBeforeTokenExpiresInSeconds: 10,
            secureRoutes: ['http://localhost:8080']
          },
          {
            configId: 'linkedin',
            authority: 'https://www.linkedin.com/oauth',
            //https://www.linkedin.com/oauth/.well-known/openid-configuration
            redirectUrl: window.location.origin,
            postLogoutRedirectUri: window.location.origin,
            clientId: 'please-enter-clientId',
            scope: 'openid profile email',
            responseType: 'token id_token',
            logLevel: LogLevel.Debug,
            silentRenew: true,
            autoUserInfo: true,
            useRefreshToken: true,
            ignoreNonceAfterRefresh: true,
            allowUnsafeReuseRefreshToken: true,
            triggerRefreshWhenIdTokenExpired: false,
            checkRedirectUrlWhenCheckingIfIsCallback: true,
            silentRenewUrl: window.location.origin + '/silent-renew.html',
            renewTimeBeforeTokenExpiresInSeconds: 10,
            secureRoutes: ['http://localhost:8080']
          }]
}
