export interface HttpConnectorFormData {
  // Basic Information
  name: string;
  description: string;
  hosts: string;
  protocol: 'http' | 'https';
  
  // Authentication
  authType: 'HTTP' | 'HTTPS_BASIC' | 'HTTPS_API_KEY' | 'HTTPS_BEARER' | 'HTTPS_CLIENT_CERT' | 'HTTPS_OIDC';
  
  // Basic Auth fields (HTTP & HTTPS_BASIC)
  username?: string;
  password?: string;
  
  // API Key Auth fields (HTTPS_API_KEY)
  apiKey?: string;
  
  // Bearer Token Auth fields (HTTPS_BEARER)
  bearerToken?: string;
  
  // Client Certificate Auth fields (HTTPS_CLIENT_CERT)
  clientKeystoreFile?: File | string;
  clientKeystorePassword?: string;
  clientKeyPassword?: string;
  
  // OIDC Auth fields (HTTPS_OIDC)
  clientId?: string;
  clientSecret?: string;
  issuer?: string;
  authorizationEndpoint?: string;
  tokenEndpoint?: string;
  userinfoEndpoint?: string;
  endSessionEndpoint?: string;
  redirectUri?: string;
  scopes?: string;
  responseType?: string;
  
  // SSL/TLS Configuration (for HTTPS)
  caCertificateFingerprint?: string;
  caCertificateFile?: File | string;
  sslEnabledProtocols?: string;
  sslProtocol?: string;
  
  // Advanced Settings
  timeout: number;
  maxRetries: number;
  followRedirects: boolean;
  
  // Additional metadata
  lastTested?: Date;
  status?: 'VALID' | 'INVALID' | 'PENDING';
}

export interface AuthTypeOption {
  value: 'HTTP' | 'HTTPS_BASIC' | 'HTTPS_API_KEY' | 'HTTPS_BEARER' | 'HTTPS_CLIENT_CERT' | 'HTTPS_OIDC';
  label: string;
  icon: string;
  description: string;
}

export const AUTH_TYPES: AuthTypeOption[] = [
  { 
    value: 'HTTP', 
    label: 'HTTP (Plain)',
    icon: 'fa-globe',
    description: 'Basic HTTP connection with optional basic authentication' 
  },
  { 
    value: 'HTTPS_BASIC', 
    label: 'HTTPS with Basic Auth',
    icon: 'fa-user-lock',
    description: 'Encrypted connection with username/password authentication' 
  },
  { 
    value: 'HTTPS_API_KEY', 
    label: 'HTTPS with API Key',
    icon: 'fa-key',
    description: 'Encrypted connection with API key authentication' 
  },
  { 
    value: 'HTTPS_BEARER', 
    label: 'HTTPS with Bearer Token',
    icon: 'fa-shield-alt',
    description: 'Encrypted connection with bearer token (JWT)' 
  },
  { 
    value: 'HTTPS_CLIENT_CERT', 
    label: 'HTTPS with Client Certificate',
    icon: 'fa-certificate',
    description: 'Mutual TLS with client certificate authentication' 
  },
  { 
    value: 'HTTPS_OIDC', 
    label: 'HTTPS with OpenID Connect',
    icon: 'fa-id-card',
    description: 'OpenID Connect for SSO authentication' 
  }
];
