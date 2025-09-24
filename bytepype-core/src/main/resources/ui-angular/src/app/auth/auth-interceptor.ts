import { OidcSecurityService } from "angular-auth-oidc-client";
import {
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpRequest,
} from "@angular/common/http";
import { Observable, take } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private oidcSecurityService: OidcSecurityService,
  ) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (!req.url.includes('/api/')) {
      return next.handle(req);
    }

    const matchingConfigId = localStorage.getItem('active-provider');
    if (!matchingConfigId) {
      return next.handle(req);
    }

    this.oidcSecurityService.getIdToken(matchingConfigId).subscribe(token => {
      if (!token) {
        return;
      }
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token),
      });
    });
    return next.handle(req);
  }
}