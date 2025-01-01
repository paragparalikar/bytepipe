import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

export function tenantHeaderInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    let oauth2ConfigId = localStorage.getItem('oauth2-config-id');
    let authorizationHeader = req.headers.get('Authorization');
    let providers = ["google", "microsoft", "linkedin", "github"];
    let providerIndex = providers.indexOf(oauth2ConfigId ?? "");
    if(oauth2ConfigId && authorizationHeader) {
        const reqWithHeader = req.clone({
            headers: req.headers.set('Authorization', authorizationHeader + providerIndex),
        });
        return next(reqWithHeader);
    } else {
        return next(req);
    }
    
}