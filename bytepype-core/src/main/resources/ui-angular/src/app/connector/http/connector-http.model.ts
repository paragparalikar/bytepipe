import { Connector } from "../connector.model";

export class HttpConnector extends Connector {
    constructor(value?: Partial<HttpConnector>) {
        super(value);
        Object.assign(this, value);
    }
    
    baseUrl?: string | null;
    authType: 'NONE' | 'BASIC' | 'BEARER' | 'API_KEY' = 'NONE';
    username?: string | null;
    password?: string | null;
    token?: string | null;
    apiKey?: string | null;
    apiKeyHeader?: string | null;
    headers?: { [key: string]: string } | null;
    timeout?: number | null;
    maxRetries?: number | null;
    sslVerification: boolean = true;
    followRedirects: boolean = true;
}
