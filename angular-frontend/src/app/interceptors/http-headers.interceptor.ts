import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError as obsevableThrowError } from "rxjs";
import { catchError} from "rxjs/operators";

@Injectable()
export class HttpHeadersInterceptor implements HttpInterceptor{
    constructor(){}
    
    intercept(
        req: HttpRequest<any>, 
        next: HttpHandler
        ): Observable<HttpEvent<any>> {
        req =req.clone({
            setHeaders:{

            },
            setParams:{

            }
        })
        return next.handle(req);

    }
}