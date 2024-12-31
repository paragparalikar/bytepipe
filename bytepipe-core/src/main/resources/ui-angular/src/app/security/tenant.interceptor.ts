import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";

export function tenantHeaderInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    let oauth2ConfigId = localStorage.getItem('oauth2-config-id');
    if(oauth2ConfigId) {
        const reqWithHeader = req.clone({
            headers: req.headers.set('oauth2-config-id', oauth2ConfigId ?? ''),
        });
        return next(reqWithHeader);
    } else {
        return next(req);
    }
    
}