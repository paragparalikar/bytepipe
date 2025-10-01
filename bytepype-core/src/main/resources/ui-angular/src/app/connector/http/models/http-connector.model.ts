export interface HttpConnectorFormData {
  // Basic Information
  name: string;
  description: string;
  baseUrl: string;
  
  // Authentication
  authType: 'NONE' | 'BASIC' | 'BEARER' | 'API_KEY';
  username?: string;
  password?: string;
  token?: string;
  apiKey?: string;
  apiKeyHeader?: string;
  
  // Advanced Settings
  timeout: number;
  maxRetries: number;
  sslVerification: boolean;
  followRedirects: boolean;
  
  // Additional metadata
  lastTested?: Date;
  status?: 'VALID' | 'INVALID' | 'PENDING';
}

export interface AuthTypeOption {
  value: 'NONE' | 'BASIC' | 'BEARER' | 'API_KEY';
  label: string;
  icon: string;
  description: string;
}

export const AUTH_TYPES: AuthTypeOption[] = [
  { 
    value: 'NONE', 
    label: 'No Authentication',
    icon: 'fa-ban',
    description: 'No authentication required' 
  },
  { 
    value: 'BASIC', 
    label: 'Basic Authentication',
    icon: 'fa-user-lock',
    description: 'Username and password authentication' 
  },
  { 
    value: 'BEARER', 
    label: 'Bearer Token',
    icon: 'fa-key',
    description: 'Bearer token authentication' 
  },
  { 
    value: 'API_KEY', 
    label: 'API Key',
    icon: 'fa-code',
    description: 'API key authentication' 
  }
];
